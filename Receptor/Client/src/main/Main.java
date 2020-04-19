package main;

import controladores.ControladorVentana;

import controladores.ControladorVentanaIngresaNombre;

import ventanas.Ventana;
import ventanas.VentanaIngresaNombre;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        VentanaIngresaNombre ventana = new VentanaIngresaNombre("");
        ControladorVentanaIngresaNombre controlador = new ControladorVentanaIngresaNombre(ventana);
        ventana.setControlador(controlador);
        ventana.abrir();
    }
}
