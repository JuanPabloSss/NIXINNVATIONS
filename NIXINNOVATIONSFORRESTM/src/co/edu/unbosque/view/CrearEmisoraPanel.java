package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.view.Entrada;

public class CrearEmisoraPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controller controller;
    private Entrada nombreBorde;
    private Entrada generoBorde;
    private JComboBox tipoSelector;

    // Constructor
    public CrearEmisoraPanel(Controller controller) {
        this.controller = controller;
        // Inicializar entradas de texto
        nombreBorde = new Entrada();
        generoBorde = new Entrada();
        render();
    }
    
    // Método para obtener los datos de la emisora
    public String[] getDatosEmi() {
        String[] datosEmi = new String[5];
        datosEmi[0] = nombreBorde.getContEntrada();
        datosEmi[1] = generoBorde.getContEntrada();
        datosEmi[2] = (String) tipoSelector.getSelectedItem();
        return datosEmi;
    }
    
    // Método para configurar el ComboBox
    private void configComboBoxes() {
        String[] tipo = {"AM","FM","Streaming"};
        tipoSelector = new JComboBox(tipo);
        tipoSelector.setBackground(Color.decode("#f0e8d8"));
    }
    
    // Método para renderizar la interfaz
    private void render() {
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Título
        JLabel titulo = new JLabel("Crear Tu Emisora");
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        titulo.setFont(new Font("Roboto", Font.BOLD, 22));
        
        // Botón para crear la emisora
        JButton crearEmiBoton = new JButton("Crear Emisora");
        crearEmiBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        crearEmiBoton.setName("crearEmisora");
        crearEmiBoton.setBackground(Color.decode("#e2d1c0"));
        crearEmiBoton.addMouseListener(this.controller);
        
        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.add(crearEmiBoton);
        
        // Agregar componentes al panel principal
        mainPanel.add(titulo);
        mainPanel.add(nombreBorde.getBorde("Nombre de tu Emisora", "nombreEmi"));
        mainPanel.add(generoBorde.getBorde("Genero de tu Emisora", "GenerEmi"));
        JLabel modeLabel = new JLabel("Modo de transmisión");
        modeLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        mainPanel.add(modeLabel);  
        configComboBoxes();
        mainPanel.add(tipoSelector);
        mainPanel.add(buttonPanel);
        
        this.setSize(400, 400); 
        this.setResizable(false); 
        this.add(mainPanel); 
    }
}
