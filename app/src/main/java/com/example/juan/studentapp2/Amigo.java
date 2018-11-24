package com.example.juan.studentapp2;

public class Amigo {

    private String carne;
    private String calificacion;

    public Amigo (String carne, String calificacion){
        this.carne = carne;
        this.calificacion = calificacion;
    }

    public String getCarne() {
        return carne;
    }

    public String getCalificacion() {
        return calificacion;
    }
}
