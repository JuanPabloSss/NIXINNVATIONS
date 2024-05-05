package co.edu.unbosque.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.border.Border;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.EmisoraDTO;
import co.edu.unbosque.model.persistance.CancionDAO;

public class ReproducirEmisoraPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controller controller;
    private UUID listaDeCancionesS;
    private UUID reproDeCancionesS;
    private JPanel mainPane;
    private JPanel listaCancion;
    private JPanel reproCancion;
    private JLabel nombreE;
    private JLabel generoE;
    private JLabel tipoE;
    private CancionDAO cancionDao;
    private ArrayList<CancionDTO> cancionRepro;
    private EmisoraDTO iniciarEmisora;


    public ReproducirEmisoraPanel(Controller control, EmisoraDTO initialProgram) {
        this.controller = control;
        this.iniciarEmisora = initialProgram;
        cancionRepro = new ArrayList<CancionDTO>();
        cancionDao = new CancionDAO("../NIXINNOVATIONSFORRESTM/src/registro");
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));
        render();
    }

    public void setListaDeCancionesS(UUID listaDeCancionesS) {
        this.listaDeCancionesS = listaDeCancionesS;
    }

    public UUID getListaDeCancionesS() {
        return this.listaDeCancionesS;
    }

    public void setReproDeCancionesS(UUID cancion) {
        reproDeCancionesS = cancion;
    }

    public UUID getReproDeCancionesS() {
        return reproDeCancionesS;
    }

    public EmisoraDTO getActiveProgram() {
        return iniciarEmisora;
    }

    // Método para configurar la emisora activa en la interfaz de usuario y actualizar los componentes relevantes
    public void setIniciarEmisora(EmisoraDTO activeProgram) {
        iniciarEmisora = activeProgram;
        nombreE.setText(activeProgram.getNombre());
        generoE.setText(activeProgram.getGenero());
        tipoE.setText(activeProgram.getModoTransmision());
        listaDeCancionesS = UUID.randomUUID();
        actualizarListaC();
        cancionRepro.clear();
        actualizarReproC();
    }

    public void setCancionRepro(ArrayList<CancionDTO> Songs) {
        this.cancionRepro = Songs;
    }

    public ArrayList<CancionDTO> getCancionRepro() {
        return this.cancionRepro;
    }

 // Método para acciones de reproducción
    
    public void reproAccion(String accion, CancionDTO cancion) {
        switch (accion) {
            case "añadirCancion":
                this.cancionRepro.add(0,cancion);
                break;
        }
    }
 // Métodos para actualizar la interfaz de usuario

    public void actualizarListaC() {
        listaCancion.removeAll();
        listaCancion.add(this.cargarListaCancion());
        listaCancion.repaint();
        listaCancion.revalidate();
    }

    public void actualizarReproC() {
        reproCancion.removeAll();
        reproCancion.add(this.cargarReproCancion());
        reproCancion.repaint();
        reproCancion.revalidate();
    }
 // Métodos de renderización de la interfaz gráfica

    private void render() {
        JPanel topMargin = new JPanel();
        topMargin.setMaximumSize(new Dimension(1100, 10));
        JPanel bottomMargin = new JPanel();
        bottomMargin.setMaximumSize(new Dimension(1100, 10));

        JPanel panelListaCancion = this.getListaCancionesPanel();
        JPanel panelReproCancion = this.getReproCancionesPanel();
        panelListaCancion.setBackground(Color.decode("#f0e8d8"));
        panelReproCancion.setBackground(Color.decode("#f0e8d8"));
      
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.LINE_AXIS));
        
        JPanel centerSepparator = new JPanel();
        centerSepparator.setMaximumSize(new Dimension(10, 10));
        
        centerPane.add(panelListaCancion);
        centerPane.add(centerSepparator);
        centerPane.add(panelReproCancion);

        mainPane.add(topMargin);
        mainPane.add(bottomMargin);
        mainPane.add(centerPane);

        this.setSize(860, 600);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle(iniciarEmisora.getNombre()+" / "+ iniciarEmisora.getGenero()+" / "+ iniciarEmisora.getModoTransmision()); 
        this.add(mainPane);
    }
 // Métodos para obtener paneles de lista y reproducción de canciones

    private JPanel getListaCancionesPanel() {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(400, 500));
        panel.setMinimumSize(new Dimension(400, 500));
        panel.setBackground(Color.decode("#f0e8d8"));
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#c4a38e"), 2, true));
        panel.setLayout(new BorderLayout());

        // -- song actions
        JPanel mainActions = new JPanel();
        mainActions.setLayout(new GridLayout(1, 2));
        mainActions.setPreferredSize(new Dimension(600, 50));
        mainActions.setMaximumSize(new Dimension(600, 50));
        mainActions.setMinimumSize(new Dimension(600, 50));

        JButton crearCBoton = new JButton("Crear Cancion");
        crearCBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        crearCBoton.setName("abrirCreacionDeCancion");
        crearCBoton.addMouseListener(this.controller);
        crearCBoton.setBackground(Color.decode("#e2d1c0"));

        mainActions.add(crearCBoton);

        // -- cue actions
        JPanel reproAccion = new JPanel();
        reproAccion.setLayout(new GridLayout(1, 4));
        reproAccion.setPreferredSize(new Dimension(600, 50));
        reproAccion.setMaximumSize(new Dimension(600, 50));
        reproAccion.setMinimumSize(new Dimension(600, 50));

        JButton agregarCBoton = new JButton("Agregar");
        agregarCBoton.setFont(new Font("Roboto", Font.BOLD, 20));
        agregarCBoton.setName("añadirCListaDeRepr");
        agregarCBoton.addMouseListener(this.controller);
        agregarCBoton.setBackground(Color.decode("#e2d1c0"));

        reproAccion.add(agregarCBoton);

        // -- Song List
        this.listaCancion = new JPanel();
        this.listaCancion.add(this.cargarListaCancion());

        JScrollPane scrollPane = new JScrollPane(this.listaCancion);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // -- Final config
        panel.add(mainActions, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(reproAccion, BorderLayout.SOUTH);
        
        return panel;
    }

    private JScrollPane cargarListaCancion() {
        JPanel listaContent = new JPanel();
        listaContent.setLayout(new BoxLayout(listaContent, BoxLayout.PAGE_AXIS));
        ArrayList<CancionDTO> canciones = cancionDao.listaCanciones(iniciarEmisora.getGenero());
        canciones.forEach((cancion) -> {
            listaContent.add(cancionDat(cancion));
        });
        JScrollPane cancionScroll = new JScrollPane(listaContent);
        cancionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cancionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return cancionScroll;
    }

    private JPanel getReproCancionesPanel() {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(400, 500));
        panel.setMinimumSize(new Dimension(400, 500));
        panel.setBackground(Color.decode("#f0e8d8"));
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#c4a38e"), 2, true));
        panel.setLayout(new BorderLayout());

        // -- cue actions
        JPanel mainActions = new JPanel();
        mainActions.setLayout(new GridLayout(1, 2));
        mainActions.setPreferredSize(new Dimension(450, 50));

        JButton iniciarBoton = new JButton(new ImageIcon(getClass().getResource("/iconos/play.png")));
        iniciarBoton.setName("iniciarCRepro");
        iniciarBoton.addMouseListener(this.controller);
        iniciarBoton.setBackground(Color.decode("#e2d1c0"));

        JButton pausaBoton = new JButton(new ImageIcon(getClass().getResource("/iconos/pausa.png")));
        pausaBoton.setName("pausarCRepro");
        pausaBoton.addMouseListener(this.controller);
        pausaBoton.setBackground(Color.decode("#e2d1c0"));

        JButton siguienteBoton = new JButton(new ImageIcon(getClass().getResource("/iconos/siguiente.png")));
        siguienteBoton.setName("siguienteCRepro");
        siguienteBoton.addMouseListener(this.controller);
        siguienteBoton.setBackground(Color.decode("#e2d1c0"));

        // -- Song List
        this.reproCancion = new JPanel();
        JScrollPane scrollPane = new JScrollPane(this.reproCancion);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainActions.add(iniciarBoton);
        mainActions.add(pausaBoton);
        mainActions.add(siguienteBoton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(mainActions, BorderLayout.NORTH);

        return panel;
    }

    private JScrollPane cargarReproCancion() {
        JPanel listaContent = new JPanel();
        listaContent.setLayout(new BoxLayout(listaContent, BoxLayout.PAGE_AXIS));
        this.cancionRepro.forEach((cancion) -> {
            listaContent.add(this.reproDat(cancion));
        });
        JScrollPane cancionReproScroll = new JScrollPane(listaContent);
        cancionReproScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cancionReproScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return cancionReproScroll;
    }

    private JPanel cancionDat(CancionDTO song) {
        Color background;
        Color fontColor;
        JPanel cDat = new JPanel();
        cDat.setPreferredSize(new Dimension(350, 55));
        cDat.setMaximumSize(new Dimension(350, 55));
        cDat.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cDat.setLayout(new GridLayout(2, 1));
        JLabel titulo = new JLabel(song.getNombre());
        titulo.setFont(new Font("Roboto", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JPanel descripcion = new JPanel();
        descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel artista = new JLabel(song.getArtista());
        artista.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel genero = new JLabel(song.getGenero());
        genero.setFont(new Font("Helvetica", Font.PLAIN, 15));
        JLabel punto = new JLabel("\u2022");
        
        punto.setFont(new Font("Helvetica", Font.BOLD, 15));
        artista.setHorizontalAlignment(SwingConstants.CENTER);
        genero.setHorizontalAlignment(SwingConstants.CENTER);
        punto.setHorizontalAlignment(SwingConstants.CENTER);
        artista.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        genero.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        punto.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        if (song.getId().equals(this.listaDeCancionesS)) {
            background = Color.decode("#c4a38e");
            fontColor = Color.WHITE;
            cDat.setBackground(background);
            titulo.setForeground(fontColor);
            descripcion.setBackground(background);
            artista.setForeground(fontColor);
            genero.setForeground(fontColor);
            punto.setForeground(fontColor);
        } else {
            if (song.isActive()) {
                background = Color.WHITE;
                fontColor = Color.BLACK;
                cDat.setBackground(background);
                titulo.setForeground(fontColor);
                descripcion.setBackground(background);
                artista.setForeground(fontColor);
                genero.setForeground(fontColor);
                punto.setForeground(fontColor);
                cDat.addMouseListener(controller);
            } else {
                background = Color.decode("#e2d1c0");
                fontColor = Color.decode("#d3baa7");
                cDat.setBackground(background);
                titulo.setForeground(fontColor);
                descripcion.setBackground(background);
                artista.setForeground(fontColor);
                genero.setForeground(fontColor);
                punto.setForeground(fontColor);
            }
        }

        descripcion.add(artista);
        descripcion.add(punto);
        descripcion.add(genero);

        cDat.setName("Lista:" + song.getId().toString());
        cDat.add(titulo);
        cDat.add(descripcion);

        return cDat;
    }

    private JPanel reproDat(CancionDTO song) {
    	 Color background;
         Color fontColor;
         JPanel rDat = new JPanel();
         rDat.setPreferredSize(new Dimension(350, 55));
         rDat.setMaximumSize(new Dimension(350, 55));
         rDat.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
         rDat.setLayout(new GridLayout(2, 1));
         JLabel titulo = new JLabel(song.getNombre());
         titulo.setFont(new Font("Roboto", Font.BOLD, 22));
         titulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
         JPanel descripcion = new JPanel();
         descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
         JLabel artista = new JLabel(song.getArtista());
         artista.setFont(new Font("Roboto", Font.PLAIN, 15));
         JLabel genero = new JLabel(song.getGenero());
         genero.setFont(new Font("Helvetica", Font.PLAIN, 15));
         JLabel punto = new JLabel("\u2022");
         
         punto.setFont(new Font("Helvetica", Font.BOLD, 15));
         artista.setHorizontalAlignment(SwingConstants.CENTER);
         genero.setHorizontalAlignment(SwingConstants.CENTER);
         punto.setHorizontalAlignment(SwingConstants.CENTER);
         artista.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
         genero.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
         punto.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

         if (song.getId().equals(this.listaDeCancionesS)) {
        	 background = Color.WHITE;
             fontColor = Color.BLACK;
             rDat.setBackground(background);
             titulo.setForeground(fontColor);
             descripcion.setBackground(background);
             artista.setForeground(fontColor);
             genero.setForeground(fontColor);
             punto.setForeground(fontColor);
         } else {
             if (song.isActive()) {
                 background = Color.WHITE;
                 fontColor = Color.BLACK;
                 rDat.setBackground(background);
                 titulo.setForeground(fontColor);
                 descripcion.setBackground(background);
                 artista.setForeground(fontColor);
                 genero.setForeground(fontColor);
                 punto.setForeground(fontColor);
                 rDat.addMouseListener(controller);
             } else {
            	 background = Color.WHITE;
                 fontColor = Color.BLACK;
                 rDat.setBackground(background);
                 titulo.setForeground(fontColor);
                 descripcion.setBackground(background);
                 artista.setForeground(fontColor);
                 genero.setForeground(fontColor);
                 punto.setForeground(fontColor);
             }
         }

         descripcion.add(artista);
         descripcion.add(punto);
         descripcion.add(genero);

         rDat.setName("LReproduccion:" + song.getId().toString());
         rDat.add(titulo);
         rDat.add(descripcion);

         return rDat;
    }
}
