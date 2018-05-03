package com.upb.ti.easypaper;

public class Producto {
    private String Nombre;
    private int cantidad;
    private String id;
    private double precio;

    public Producto() {
        Nombre = "";
        cantidad = 0;
        precio = 0;
    }

    public Producto(String nombre, int cantidad,double precio) {
        this.Nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
