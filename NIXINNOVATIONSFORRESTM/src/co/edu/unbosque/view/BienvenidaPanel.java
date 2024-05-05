package co.edu.unbosque.view;

import javax.swing.*;
import co.edu.unbosque.controller.Controller;
import java.awt.*;

public class BienvenidaPanel extends JFrame {

    private static final long serialVersionUID = 1L;
   
    private Controller control;

    // Constructor
    public BienvenidaPanel(Controller controller) {
        control = controller;
        // Inicializar la interfaz de usuario
        initializeUI();
    }

    // Método para inicializar la interfaz de usuario
    private void initializeUI() {
        // Panel principal
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new GridLayout(3, 1, 0, 10)); 
        mainPane.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        mainPane.setBackground(Color.decode("#f0e8d8"));
        
        // Título
        JLabel titulo = new JLabel("¡Bienvenido!");
        titulo.setFont(new Font("Arial", Font.BOLD, 100));
        titulo.setForeground(Color.decode("#3c3c3c"));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
  
        // Descripción del proyecto
        JLabel descripcion = new JLabel("<html><div style='text-align: center; width: 600px;'>"
                + "Este programa te permite acceder y gestionar tu propia emisora de radio de manera personalizada. <br>"
                + "Podrás crear y escuchar tus emisoras favoritas, ajustando la programación y el contenido según tus preferencias. <br>"
                + "Disfruta de una experiencia única de radio adaptada completamente a tus gustos y necesidades.</div></html>");
        descripcion.setFont(new Font("Arial", Font.PLAIN, 15));
        descripcion.setForeground(Color.decode("#3c3c3c"));
        descripcion.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Botón Continuar
        JButton continueButton = new JButton("Continuar");
        continueButton.setBackground(Color.decode("#e2d1c0"));
        continueButton.setFont(new Font("Arial", Font.BOLD, 24));
        continueButton.setForeground(Color.decode("#3c3c3c"));
        continueButton.setName("abrirListaEmisoras");
        continueButton.addMouseListener(this.control);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#f0e8d8"));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(continueButton);

        // Agregar componentes al panel principal
        mainPane.add(titulo);
        mainPane.add(descripcion);
        mainPane.add(buttonPanel);
        
        getContentPane().add(mainPane);

        setSize(810, 500); 
        setResizable(false); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("¡Bienvenido!");
    }
}
