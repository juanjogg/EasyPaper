package com.upb.ti.easypaper;

public class Documento {
    private String nombreDocumento;
    private String nombreProfesor;
    private String nombreFacultad;
    private String nombreMateria;
    private int numeroPaginas;

    public Documento(String nombreDocumento, String nombreProfesor, String nombreFacultad, String nombreMateria, int numeroPaginas) {
        this.nombreDocumento = nombreDocumento;
        this.nombreFacultad = nombreFacultad;
        this.nombreMateria = nombreMateria;
        this.nombreProfesor = nombreProfesor;
        this.numeroPaginas = numeroPaginas;
    }

    public Documento() {

    }
}
