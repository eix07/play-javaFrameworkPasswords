/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkAnotaciones.Objetos;

import FrameworkAnotaciones.AnotationReader;

/**
 *
 * @author Labing
 */
public class Main {

    public static AnotationReader tomateReader;

    public static void main(String[] args) {
            
            tomateReader = new AnotationReader("src/FrameworkAnotaciones/Descriptores/TomateDescriptor.txt");
            tomateReader.leerConAnotaciones();
            tomateReader.leerTomate();  //Muestra los datos por consola
            tomateReader.EscribirAleatorio(); //Escribe en el archivo de salida Aleatoreamente
            
                //Vamos a correrlo !!!!!!
    }
}
