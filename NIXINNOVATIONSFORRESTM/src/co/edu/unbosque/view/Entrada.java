package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Entrada {
    
    JTextField seccion;
    
    // Método para obtener el borde con etiqueta y nombre
    public JPanel getBorde(String etiqueta, String nombre) {
        // Panel para la entrada
        JPanel entrada = new JPanel();
        entrada.setLayout(new GridLayout(2, 1));
        // Etiqueta
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Roboto", Font.PLAIN, 15));
        // Campo de texto
        this.seccion = new JTextField();
        seccion.setName(nombre);
        seccion.setBorder(BorderFactory.createLineBorder(Color.decode("#c4a38e"), 2, true));
        // Agregar componentes al panel de entrada
        entrada.add(label);
        entrada.add(seccion);
        return entrada;
    }
    
    // Método para obtener el contenido de la entrada
    public String getContEntrada() {
        return this.seccion.getText();
    }
}
