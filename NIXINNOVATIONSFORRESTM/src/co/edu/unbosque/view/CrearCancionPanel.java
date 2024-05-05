package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.view.Entrada;

public class CrearCancionPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private Entrada nombreBorde;
	private Entrada artistaBorde;
	private Entrada generoBorde;
	private String archivo;

	public CrearCancionPanel(Controller controller) {
		this.controller = controller;
		nombreBorde = new Entrada();
		artistaBorde = new Entrada();
		generoBorde = new Entrada();
		render();
	}

	public Entrada getNombreBorde() {
		return nombreBorde;
	}

	public Entrada getArtistaBorde() {
		return artistaBorde;
	}

	public Entrada getGeneroBorde() {
		return generoBorde;
	}
	
	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	// Método para renderizar la interfaz
    private void render() {
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Título
        JLabel titulo = new JLabel("Crear Tu Cancion");
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        titulo.setFont(new Font("Roboto", Font.BOLD, 22));
        
        // Botón para crear la canción
        JButton crearCBoton = new JButton("Crear Cancion");
        crearCBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        crearCBoton.setBackground(Color.decode("#e2d1c0"));
        crearCBoton.addMouseListener(controller);
        crearCBoton.setName("crearCancion");
        
        // Botón para seleccionar el archivo de la canción
        JButton selecionarFBoton = new JButton("Seleccionar Archivo");
        selecionarFBoton.setName("crearArchivo");
        selecionarFBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        selecionarFBoton.setBackground(Color.decode("#e2d1c0"));
        selecionarFBoton.addMouseListener(this.controller);
        
        // Agregar componentes al panel principal
        mainPanel.add(titulo);
        mainPanel.add(this.nombreBorde.getBorde("Nombre de tu Cancion", "NombreCancion"));
        mainPanel.add(this.artistaBorde.getBorde("Artista de tu Cancion", "ArtistaCancion"));
        mainPanel.add(this.generoBorde.getBorde("Genero de tu Cancion", "GeneroCancion"));
        mainPanel.add(new JPanel());
        mainPanel.add(selecionarFBoton);
        mainPanel.add(new JPanel());
        mainPanel.add(crearCBoton);
        
        this.setSize(400, 500); 
        this.setResizable(false); 
        this.add(mainPanel); 
    }
}

