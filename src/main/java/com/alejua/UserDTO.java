package com.alejua;

public class UserDTO {
    private String nombre;

    public UserDTO() {
    }

    public UserDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
