package ventanas;

import controladores.ControladorVentanaIngresaNombre;

import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JTextField;

import vista.InterfazVistaIngresaNombre;

public class VentanaIngresaNombre extends JFrame implements InterfazVistaIngresaNombre {
    private ControladorVentanaIngresaNombre controlador;
    private JTextField textFieldNombre = new JTextField();
    private JButton botonContinuar = new JButton("Continuar");
    
    public VentanaIngresaNombre(String args0) {
        super(args0);
        botonContinuar.setActionCommand("ContinuarClickeado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenedorPrincipal = this.getContentPane();
        contenedorPrincipal.setLayout(new BorderLayout());
        JLabel labelNombre = new JLabel("Ingrese su nombre");
        contenedorPrincipal.add(labelNombre, BorderLayout.NORTH);
        contenedorPrincipal.add(textFieldNombre, BorderLayout.CENTER);
        contenedorPrincipal.add(botonContinuar, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener controlador) {
        this.controlador = (ControladorVentanaIngresaNombre)controlador;
        botonContinuar.addActionListener(controlador);
    }

    @Override
    public void cerrar() {
        setVisible(false);
    }

    @Override
    public void abrir() {
        pack();
        setBounds(200,200,200,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public String getNombre() {
        return textFieldNombre.getText();
    }
}
