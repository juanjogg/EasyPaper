package com.upb.ti.easypaper;

import java.util.ArrayList;

public class Papeleria {
    private String nombre;
    private String disponibilidad;
    private String ubicacion;
    private String idPapeleria;
    private ArrayList<Documento> documentos;
    private ArrayList<String> servicios;
    private ArrayList<Producto> productos;


    public Papeleria(String nombre,String ubicacion,String idPapeleria, String disponiblidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        disponibilidad = disponiblidad;
        this.idPapeleria = idPapeleria;
        this.documentos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public Papeleria(String nombre, String ubicacion, String disponibilidad) {
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.nombre = nombre;
        this.documentos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.productos = new ArrayList<>();
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

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }
}
