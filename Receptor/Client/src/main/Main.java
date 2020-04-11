package main;

import controladores.ControladorVentana;

import ventanas.Ventana;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana("Elegir modo usuario");
        ControladorVentana controlador = new ControladorVentana(ventana);
        ventana.setControlador(controlador);
        ventana.abrir();
    }
}
