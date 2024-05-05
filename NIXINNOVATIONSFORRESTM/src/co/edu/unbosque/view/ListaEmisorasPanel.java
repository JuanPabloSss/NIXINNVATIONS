package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.EmisoraDTO;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.persistance.EmisoraDAO;

public class ListaEmisorasPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private EmisoraDAO emisoraDao;
	private UUID selecionarEmisora;
	private JPanel mainPane;
	private JPanel panelListaEmisoras;
	// Constructor
    public ListaEmisorasPanel(Controller control) {
        this.controller = control;
        // Inicializar DAO de Emisoras
        emisoraDao = new EmisoraDAO("../NIXINNOVATIONSFORRESTM/src/registro");
        render();
    }

    // Método para obtener la emisora seleccionada
    public UUID getSelecionarEmisora() {
        return selecionarEmisora;
    }

    // Método para establecer la emisora seleccionada
    public void setSelecionarEmisora(UUID selectedProgram) {
        selecionarEmisora = selectedProgram;
    }
    
    // Método para actualizar la lista de emisoras
    public void actualizarEmi() {
        panelListaEmisoras.removeAll();
        panelListaEmisoras.add(this.cargarListaEmi());
        panelListaEmisoras.repaint();
        panelListaEmisoras.revalidate();
    }

    // Método para renderizar la interfaz
    private void render() {
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        mainPane.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        mainPane.setBackground(Color.WHITE);

        // Parte de arriba
        JLabel titulo = new JLabel("Selecciona Tu Emisora Favorita");
        titulo.setFont(new Font("Roboto", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setPreferredSize(new Dimension(350, 60));

        JPanel MargenSup = new JPanel();
        MargenSup.setBackground(Color.WHITE);
        MargenSup.setPreferredSize(new Dimension(350, 10));

        // Boton create
        JPanel BarraBotonA = new JPanel();
        BarraBotonA.setBackground(Color.WHITE);
        BarraBotonA.setPreferredSize(new Dimension(650, 50));
        BarraBotonA.setLayout(new GridLayout(1, 1));

        JButton nuevaEmiBoton = new JButton("Crear Nueva Emisora");
        nuevaEmiBoton.setBackground(Color.decode("#e2d1c0"));
        nuevaEmiBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        nuevaEmiBoton.setName("abrirCrearEmisora");
        nuevaEmiBoton.addMouseListener(controller);

        BarraBotonA.add(nuevaEmiBoton);

        // Lista De Emisoras
        panelListaEmisoras = new JPanel();
        panelListaEmisoras.setPreferredSize(new Dimension(315, 600));
        panelListaEmisoras.setBackground(Color.decode("#f0e8d8"));
        panelListaEmisoras.setBorder(BorderFactory.createLineBorder(Color.decode("#c4a38e"), 2, true));
        panelListaEmisoras.add(cargarListaEmi());
        
        JPanel MargenInfe = new JPanel();
        MargenInfe.setBackground(Color.WHITE);
        MargenInfe.setPreferredSize(new Dimension(350, 10));

        // Boton Select
        JPanel BarraBotonB = new JPanel();
        BarraBotonB.setBackground(Color.WHITE);
        BarraBotonB.setPreferredSize(new Dimension(650, 50));
        BarraBotonB.setLayout(new GridLayout(1, 1));
        
        JButton selecEmiBoton = new JButton("Seleccionar Emisora");
        selecEmiBoton.setBackground(Color.decode("#e2d1c0"));
        selecEmiBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        selecEmiBoton.setName("seleccionarEmisoraInicial");
        selecEmiBoton.addMouseListener(controller);

        BarraBotonB.add(selecEmiBoton);
        
        mainPane.add(Box.createVerticalGlue());
        mainPane.add(titulo);
        mainPane.add(MargenSup);
        mainPane.add(BarraBotonA);
        mainPane.add(panelListaEmisoras);
        mainPane.add(MargenInfe);
        mainPane.add(BarraBotonB);
        
        setSize(400, 800); 
        setResizable(false); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("Lista De Emisoras"); // Título de la ventana
        add(mainPane); 
    }

    // Método para cargar la lista de emisoras
    private JScrollPane cargarListaEmi() {
        JPanel listaContent = new JPanel();
        listaContent.setLayout(new BoxLayout(listaContent, BoxLayout.PAGE_AXIS));
        ArrayList<EmisoraDTO> emisora = emisoraDao.listaEmisoras();
        emisora.forEach((emi) -> {
            listaContent.add(emisoraDat(emi));
        });
        JScrollPane emiScroll = new JScrollPane(listaContent);
        emiScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        emiScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return emiScroll;
    }

    // Método para generar el panel de datos de una emisora
	private JPanel emisoraDat(EmisoraDTO emisora) {
		Color background;
        Color fontColor;
        JPanel dat = new JPanel();
        dat.setPreferredSize(new Dimension(280, 55));
        dat.setMaximumSize(new Dimension(280, 55));
        dat.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        dat.setLayout(new GridLayout(2, 1));
        JLabel nombreE = new JLabel(emisora.getNombre());
        nombreE.setFont(new Font("Roboto", Font.BOLD, 22));
        nombreE.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JPanel descripcion = new JPanel();
        descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel generoE = new JLabel(emisora.getGenero());
        generoE.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel tipoE = new JLabel(emisora.getModoTransmision());
        tipoE.setFont(new Font("Helvetica", Font.PLAIN, 15));
        JLabel punto = new JLabel("\u2022");
        
        punto.setFont(new Font("Helvetica", Font.BOLD, 15));
        generoE.setHorizontalAlignment(SwingConstants.CENTER);
        tipoE.setHorizontalAlignment(SwingConstants.CENTER);
        punto.setHorizontalAlignment(SwingConstants.CENTER);
        generoE.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        tipoE.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        punto.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        if (emisora.getId().equals(this.selecionarEmisora)) {
            background = Color.decode("#c4a38e");
            fontColor = Color.WHITE;
            dat.setBackground(background);
            nombreE.setForeground(fontColor);
            descripcion.setBackground(background);
            generoE.setForeground(fontColor);
            tipoE.setForeground(fontColor);
            punto.setForeground(fontColor);
        } else {
        	background = Color.WHITE;
            fontColor = Color.BLACK;
            dat.setBackground(background);
            nombreE.setForeground(fontColor);
            descripcion.setBackground(background);
            generoE.setForeground(fontColor);
            tipoE.setForeground(fontColor);
            punto.setForeground(fontColor);
            }
        

        descripcion.add(generoE);
        descripcion.add(punto);
        descripcion.add(tipoE);

        dat.setName("Emisora:" + emisora.getId().toString());
        dat.addMouseListener(controller);
        dat.add(nombreE);
        dat.add(descripcion);

        return dat;
	}
}
