/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkAnotaciones;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Labing
 */
public class AnotationReader {

    private Field[] campos;
    private Annotation[] anotaciones;
    private Class cls;
    private Lector lector;
    private String rutaArchivo;
    private LinkedList<Object> retorna;

    public AnotationReader(String rutaDescriptor) {
        try {
            this.lector = new Lector(new FileReader(rutaDescriptor));
            List<String> lista = this.lector.leerArchivo();
            cls = Class.forName(lista.get(0));
            rutaArchivo = lista.get(1);
            campos = cls.getDeclaredFields();
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EscribirConAnotaciones(Object obj) {
        try {
            FileWriter fw = new FileWriter("app/FrameworkAnotaciones/Archivos/Tomacos.txt", true); //Archivo de salida
            BufferedWriter bw = new BufferedWriter(fw);
            String valor = "";
            String valorEscribir = "";
            for (Field campo : campos) {

                //System.out.println("campo " + campo.getName());
                anotaciones = campo.getAnnotations();
                
                for (Annotation anotacion : anotaciones) {
                    // System.out.println(anotacion);
                    if (anotacion instanceof FixedWidthField) {
                        
                        int width = (((FixedWidthField) anotacion).width());
                        if (campo.getType().equals(boolean.class)) {
                            Method get = cls.getMethod("is" + capitalize(campo.getName()));
                            valor = get.invoke(obj) + "";
                        } else if(campo.getType().equals(java.util.Date.class)){
                            Method get = cls.getMethod("get" + capitalize(campo.getName()));
                            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
                            valor=dt1.format(get.invoke(obj));
                        }else {
                            Method get = cls.getMethod("get" + capitalize(campo.getName()));
                            valor = get.invoke(obj) + "";
                        } 
                        int resta = width - valor.length();
                        for (int i = 0; i < resta; i++) {
                            valor = valor + " ";
                        }
                        valorEscribir = valorEscribir + valor;
                    }
                }
            }
            bw.write(valorEscribir + "\n");
            bw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println(anotaciones.length)
    }

    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public LinkedList<Object> leerConAnotaciones() {
        retorna = new LinkedList<>();
        try {
            List<String> datos = new Lector(new FileReader(this.rutaArchivo)).leerArchivo();

            for (String dato : datos) {
                Object obj = cls.newInstance();
                int inicioVentana = 0, finVentana = 0;
                for (Field campo : campos) {
                    anotaciones = campo.getAnnotations();
                    
                    if (anotaciones.length >0 && anotaciones[0] instanceof FixedWidthField) {
                        int width = (((FixedWidthField) anotaciones[0]).width());
                        finVentana = width + inicioVentana;
                        String data = dato.substring(inicioVentana, finVentana);
                        Method get = cls.getMethod("set" + capitalize(campo.getName()), campo.getType());
                        get.invoke(obj, casteoObjeto(campo.getType(), data));
                        
                        inicioVentana = finVentana;
                        //System.out.println("campo "+data);
                        
                    }
                }
                retorna.add(obj);
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorna;
    }

    private Object casteoObjeto(Class<?> type, String data) throws ParseException {
        Object temporal = null;
        ///System.out.println("data " + data);
        //System.out.println(data);
        if (type.getCanonicalName().equalsIgnoreCase("int")) {
            temporal = Integer.parseInt(data.trim());
        } else if (type.getCanonicalName().equalsIgnoreCase("double")) {
            temporal = Double.parseDouble(data.trim());
        } else if (type.getCanonicalName().equalsIgnoreCase("boolean")) {
            temporal = Boolean.parseBoolean(data.trim());
        } else if (type.getCanonicalName().equalsIgnoreCase("java.lang.String")) {
            temporal = data;
        } else if (type.getCanonicalName().equalsIgnoreCase("java.util.Date")) {
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
            temporal = dt1.parse(data);
            //System.out.println(dt1.format(temporal));
        }
        return temporal;

    }

    public void leerTomate() {

        try {

            
            Collections.shuffle(retorna);
            for (Object lista1 : retorna) {
                System.out.println(lista1.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void EscribirAleatorio() {
        try {
            LinkedList tomatoes = retorna;
            Collections.shuffle(tomatoes);
            for (Object tomatoe : tomatoes) {
                EscribirConAnotaciones(tomatoe);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
