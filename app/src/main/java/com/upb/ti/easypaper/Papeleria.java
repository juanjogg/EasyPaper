package com.upb.ti.easypaper;

public class Papeleria {
    private String nombre;
    private Disponibilidad disponibilidad;
    private String ubicacion;

    public Papeleria(String nombre,String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        disponibilidad = Disponibilidad.Alta;
    }
}
