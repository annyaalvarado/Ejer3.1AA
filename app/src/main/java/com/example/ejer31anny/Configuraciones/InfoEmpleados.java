package com.example.ejer31anny.Configuraciones;

import java.io.Serializable;

public class InfoEmpleados implements Serializable {

    String Nombres, Apellidos, Direccion, Puesto,Edad;
    private Integer id;

    public InfoEmpleados(String nombres, String apellidos, String direccion, String puesto, String edad, Integer id) {
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        Puesto = puesto;
        Edad = edad;
        this.id = id;
    }

    public InfoEmpleados() {
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
