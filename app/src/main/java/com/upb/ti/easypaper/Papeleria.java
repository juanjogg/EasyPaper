package com.upb.ti.easypaper;

public class Papeleria {
    private String nombre;
    private Disponibilidad disponibilidad;
    private String ubicacion;
    private String idPapeleria;

    public Papeleria(String nombre,String ubicacion,String idPapeleria) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        disponibilidad = Disponibilidad.Alta;
        this.idPapeleria = idPapeleria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getIdPapeleria() {
        return idPapeleria;
    }
}
