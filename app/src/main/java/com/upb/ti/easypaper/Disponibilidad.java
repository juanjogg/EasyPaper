package com.upb.ti.easypaper;

public enum Disponibilidad {
    Alta ("Alta"),
    Media("Media"),
    Baja("Baja"),
    Cerrado("Cerrado");

    private final String disponibilidad;
    Disponibilidad(String disponibilidad){
        this.disponibilidad = disponibilidad;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }
}
