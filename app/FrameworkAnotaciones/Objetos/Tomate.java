package FrameworkAnotaciones.Objetos;

import FrameworkAnotaciones.FixedWidthField;
import java.util.Date;

/**
 *
 * @author Nosotros
 */

public class Tomate {
    
    @FixedWidthField(width=20) String color;
    @FixedWidthField(width=3) double  peso;
    @FixedWidthField(width=7)boolean estaPicho;
    @FixedWidthField(width=11)Date fechaVencimiento;
    
    public Tomate(){
        
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isEstaPicho() {
        return estaPicho;
    }

    public void setEstaPicho(boolean estaPicho) {
        this.estaPicho = estaPicho;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tomate{" + "color=" + color + ", peso=" + peso + ", estaPicho=" + estaPicho + ", fechaVencimiento=" + fechaVencimiento + '}';
    }
    
    
    
}
