package ventanas;

import controladores.ControladorVentana;

import modelo_r.mensaje.Mensaje;

import vista.InterfazVistaReceptor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Ventana extends JFrame implements InterfazVistaReceptor {
    private ControladorVentana controlador;
    private JLabel emisorLabel = new JLabel();
    private JLabel asuntoLabel = new JLabel();
    private JTextArea cuerpoTextArea = new JTextArea();
    private JButton botonApagarAlarma = new JButton("APAGAR ALARMA");
    private DefaultListModel<String> modeloListaMensajes = new DefaultListModel<>();
    JList listaMensajes = new JList();

    public Ventana(String args0) {
        super(args0);
        
        // Seteo las acciones de las properties
        botonApagarAlarma.setActionCommand("ApagarAlarma");
        botonApagarAlarma.setAlignmentX(SwingConstants.CENTER);
        cuerpoTextArea.setEditable(false);
        emisorLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
        asuntoLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
        listaMensajes.setModel(modeloListaMensajes);
        listaMensajes.setCellRenderer(getRenderer());
        
        addMouseListener();
        // Me encargo de la UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenedorPrincipal = this.getContentPane();
        contenedorPrincipal.setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(10,10,10,10));
        JPanel panelListaMensajes = new JPanel();
        panelListaMensajes.setLayout(new BorderLayout());
        JScrollPane scrollListaMensajes = new JScrollPane(listaMensajes);
        Dimension d = listaMensajes.getPreferredSize();
        d.width = 150;
        scrollListaMensajes.setPreferredSize(d);
        JLabel labelListaMensajes = new JLabel("Lista de mensajes");
        panelListaMensajes.add(scrollListaMensajes, BorderLayout.CENTER);
        panelListaMensajes.add(labelListaMensajes, BorderLayout.NORTH);
        contenedorPrincipal.add(panelListaMensajes, BorderLayout.WEST);
        contenedorPrincipal.add(panelPrincipal, BorderLayout.CENTER);
        JLabel mensajeLabel = new JLabel("Mensaje");
        panelPrincipal.add(mensajeLabel, BorderLayout.NORTH);
        
        // Panel mensaje
        JPanel panelMensaje = new JPanel();
        panelMensaje.setBorder(new EmptyBorder(10,0,10,0));
        panelMensaje.setLayout(new BorderLayout());
        JPanel panelEmisorAsunto = new JPanel();
        panelEmisorAsunto.setLayout(new GridLayout(2,1,1,1));
        panelEmisorAsunto.add(emisorLabel);
        panelEmisorAsunto.add(asuntoLabel);
        panelMensaje.add(panelEmisorAsunto, BorderLayout.NORTH);
        
        panelMensaje.add(cuerpoTextArea, BorderLayout.CENTER);
        panelPrincipal.add(panelMensaje, BorderLayout.CENTER);
        
        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        panelInferior.setAlignmentX(SwingConstants.CENTER);
        botonApagarAlarma.setPreferredSize(new Dimension(250,25));
        panelInferior.add(botonApagarAlarma);
        contenedorPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        /*
        // Agrego accion de desconectar al usuario del directorio al cerrar la aplicacion
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    controlador.notificarCambioDeEstado(false);
                }
        });
        */
    }
    
    public void setControlador(ActionListener controlador) {
        this.controlador = (ControladorVentana) controlador;
        botonApagarAlarma.addActionListener(controlador);
    }

    public void abrir() {
        pack();
        setBounds(200,200,500,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cerrar() {
        setVisible(false);
    }
    
    private void mostrarMensaje(Mensaje mensaje) {
        emisorLabel.setText("De: "+mensaje.getEmisor().getNombre());
        asuntoLabel.setText("Asunto: "+mensaje.getAsunto());
        cuerpoTextArea.setText(mensaje.getDescripcion());
    }
    
    public void agregarMensaje(Mensaje mensaje) {
        modeloListaMensajes.addElement(mensaje.getAsunto());
        mostrarMensaje(mensaje);
    }
    
    private void addMouseListener() {
        listaMensajes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                   JList list = (JList)evt.getSource();
                   if (evt.getClickCount() == 2) {
                       int index = list.locationToIndex(evt.getPoint());
                       mostrarMensaje(controlador.getMensajesRecibidos().get(index));
                   }
            }
        });
    }
    
    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer() {
          @Override
          public Component getListCellRendererComponent(JList<?> list,
              Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel listCellRendererComponent = (JLabel) super
                .getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
            listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0,
                0, 1, 0, Color.gray));
            return listCellRendererComponent;
          }
        };
    }
}
