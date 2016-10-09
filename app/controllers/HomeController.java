package controllers;

import FrameworkAnotaciones.AnotationReader;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import services.Person;
import views.html.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    AnotationReader tomateReader;
    LinkedList<Object>list;
    public Result index() {

        return ok(hola.render("SavePass.net"));
    }

    public Result requestLog(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        tomateReader = new AnotationReader("app/FrameworkAnotaciones/Descriptores/TomateDescriptor.txt");
        list=tomateReader.leerConAnotaciones();
        Logger.info("List size "+list.size());
        Person x=null;
        for (int i=list.size()-1;i>=0;i--) {
            x=(Person)list.get(i);
            Logger.info(x.getEmail()+"---------------------");
            if(x.getEmail().trim().equals(dynamicForm.get("username"))&&
                    x.getPassword().trim().equals(dynamicForm.get("password"))){
                ArrayList<String>aa=new ArrayList<>(Arrays.asList(x.getTotalPass().trim().split(" ")));
                ArrayList<String>pas=new ArrayList<>();
                ArrayList<String>ids=new ArrayList<>();
                if(aa.size()>1){
                    for (int j = 0; j <aa.size(); j++) {
                        pas.add(aa.get(j));
                        j++;
                        ids.add(aa.get(j));
                    }
                }
                Logger.info("Pases "+aa.size());
                return ok(account.render("Your account",x,pas,ids));
            }
        }
        return ok("OOPS! Email not registered");
    }

    public Result registerNew(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Logger.info("First name is: " + dynamicForm.get("firstName"));
        Logger.info("Last name is: " + dynamicForm.get("lastName"));
        Logger.info("Email is: " + dynamicForm.get("email"));
        Logger.info("Pass: " + dynamicForm.get("pass"));
        Person person=new Person(dynamicForm.get("firstName"),dynamicForm.get("lastName"),dynamicForm.get("email"),
                dynamicForm.get("pass"));
        tomateReader = new AnotationReader("app/FrameworkAnotaciones/Descriptores/TomateDescriptor.txt");
        tomateReader.EscribirConAnotaciones(person);
        return ok("The person was created: "+person.toString());
    }

    public Result addPass(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Logger.info("ID: "+dynamicForm.get("id"));
        Logger.info("pass: "+dynamicForm.get("pass"));
        Logger.info("email: "+request().getQueryString("email"));
        Person x=null;
        for(Object tomaco:list){
            x=(Person)tomaco;
            if(x.getEmail().equalsIgnoreCase(request().getQueryString("email"))){
                break;
            }
        }
        String pass=dynamicForm.get("pass");
        String id=dynamicForm.get("id");
        String psid=id+" "+pass;
        x.addPass(psid);
        tomateReader.EscribirConAnotaciones(x);
        return ok("Password added! "+id+" "+pass);
    }

}
