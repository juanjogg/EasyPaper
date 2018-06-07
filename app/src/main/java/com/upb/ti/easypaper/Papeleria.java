package com.upb.ti.easypaper;

import java.util.ArrayList;

public class Papeleria {
    private String nombre;
    private String disponibilidad;
    private String ubicacion;
    private String idPapeleria;
    private String horario;
    private String uid;
    private ArrayList<Documento> documentos;
    private ArrayList<String> servicios;
    private ArrayList<Producto> productos;


    public Papeleria(String nombre,String ubicacion,String horario,String idPapeleria,String disponiblidad, String uid) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        disponibilidad = disponiblidad;
        this.horario = horario;
        this.idPapeleria = idPapeleria;
        this.uid = uid;
        this.documentos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public Papeleria(String nombre, String ubicacion,String horario ,String disponibilidad, String uid) {
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.nombre = nombre;
        this.horario = horario;
        this.uid = uid;
        this.documentos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.productos = new ArrayList<>();
    }
    public Papeleria(String nombre, String ubicacion, String disponibilidad){
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.ubicacion = ubicacion;
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

    public String getHorario() {
        return horario;
    }

    public String getUid() {
        return uid;
    }
}
