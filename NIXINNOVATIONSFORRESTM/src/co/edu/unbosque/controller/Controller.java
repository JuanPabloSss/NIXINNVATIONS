package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.persistance.EmisoraDAO;
import co.edu.unbosque.model.persistance.CancionDAO;
import co.edu.unbosque.view.BienvenidaPanel;
import co.edu.unbosque.view.CrearCancionPanel;
import co.edu.unbosque.view.CrearEmisoraPanel;
import co.edu.unbosque.view.ListaEmisorasPanel;
import co.edu.unbosque.view.ReproducirEmisoraPanel;

public class Controller implements ActionListener, MouseListener {

    private BienvenidaPanel inicioPanel;
    private ReproducirEmisoraPanel mainUI;
    private CrearCancionPanel crearCancionPanel;
    private CrearEmisoraPanel crearEmisoraPanel;
    private ListaEmisorasPanel listaEmisorasPanel;
    private CancionDAO cancionDao;
    private EmisoraDAO emisoraDao;
    private boolean play;
    private boolean stop;
    javazoom.jl.player.Player player;
    private File cancion;
    private String ruta;
   
    // Constructor
    public Controller() {
        // Inicio de la ejecución
        System.out.println("Comenzando Ejecucion");
        // Ruta de los archivos
        ruta = "../NIXINNOVATIONSFORRESTM/src/registro";
        crearEmisoraPanel =  new CrearEmisoraPanel(this);
        emisoraDao = new EmisoraDAO(this.ruta);
        cancionDao = new CancionDAO(this.ruta);
        crearCancionPanel = new CrearCancionPanel(this);
        listaEmisorasPanel = new ListaEmisorasPanel(this);
        inicioPanel = new BienvenidaPanel(this);
        inicioPanel.setVisible(true);
    }

    // Método para reproducir una canción
    public void reproducirCancion(CancionDTO song) {
        if (!play) {
            try {
                // Crear reproductor de la canción
                cancion = new File(song.getArchivo());
                FileInputStream fis = new FileInputStream(cancion);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                play = true;
            } catch (Exception e) {
                System.out.println("Problema reproduciendo la canción");
                stop = true;
            }
            // Iniciar hilo para la reproducción
            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();
                        System.out.println("Canción Terminda");
                    } catch (Exception e) {
                    }
                }
            }.start();
        } else {
            // Detener reproducción si ya está reproduciendo
            try {
                player.close();
                play = false;
            } catch (Exception e) {
            }
        }
    }
    
    // Método para detener la reproducción de la canción actual
    public void detenerCancion() {
        try {
            play = false;
            player.close();
        } catch (Exception e1) {
            System.out.println("Canción Detenida");
            System.out.println("Canción Terminda");
        }
    }

    // Manejo de eventos de acción
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Acciones según el componente del ratón presionado
        CancionDTO song = new CancionDTO();
        String ActionName = e.getComponent().getName();
        switch (ActionName) {
            case "abrirCreacionDeCancion":
                crearCancionPanel.setVisible(true);
                break;
            case "crearCancion":
                song = new CancionDTO(UUID.randomUUID(), crearCancionPanel.getNombreBorde().getContEntrada(),
                        crearCancionPanel.getArtistaBorde().getContEntrada(),
                        crearCancionPanel.getGeneroBorde().getContEntrada(), crearCancionPanel.getArchivo(), false);
                cancionDao.crearCancion(song);
                crearCancionPanel.setVisible(false);
                mainUI.actualizarListaC();
                break;
            case "añadirCListaDeRepr":
                song = cancionDao.getIdCancion(mainUI.getListaDeCancionesS());
                mainUI.reproAccion("añadirCancion", song);
                mainUI.actualizarReproC();
                break;
            case "crearArchivo":
                // Abrir el diálogo de selección de archivo y establecer la ruta relativa
                String directorioProyecto = System.getProperty("user.dir");
                JFileChooser fc = new JFileChooser(directorioProyecto);
                fc.showOpenDialog(crearCancionPanel);
                File file = fc.getSelectedFile();
                if (file != null) {
                    String rutaAbsoluta = file.getAbsolutePath();
                    String rutaRelativa = Paths.get(directorioProyecto).relativize(Paths.get(rutaAbsoluta)).toString();
                    crearCancionPanel.setArchivo(rutaRelativa);
                } else {
                    System.out.println("No se seleccionó ningún archivo.");
                }
                break;
            case "iniciarCRepro":
                // Reproducir la canción seleccionada en la lista de reproducción
                ArrayList<CancionDTO> Cue = mainUI.getCancionRepro();
                CancionDTO active = Cue.get(Cue.size() - 1);
                reproducirCancion(active);
                Cue.remove(active);
                mainUI.setCancionRepro(Cue);
                mainUI.actualizarReproC();
                break;
            case "siguienteCRepro":
                // Reproducir la siguiente canción en la lista de reproducción
                detenerCancion();
                System.out.println("Canción Skiped");
                ArrayList<CancionDTO> UpdatedCue = mainUI.getCancionRepro();
                CancionDTO next = UpdatedCue.get(UpdatedCue.size() - 1);
                reproducirCancion(next);
                UpdatedCue.remove(next);
                mainUI.setCancionRepro(UpdatedCue);
                mainUI.actualizarReproC();
                break;
            case "pausarCRepro":
                // Pausar la reproducción de la canción actual
                detenerCancion();
                break;
            case "abrirListaEmisoras":
                // Mostrar el panel de lista de emisoras
                listaEmisorasPanel.setVisible(true);
                inicioPanel.setVisible(false);
                break;
            case "seleccionarEmisoraInicial":
                // Seleccionar una emisora inicial y mostrar su panel de reproducción
                if (mainUI != null) {
                    mainUI.setIniciarEmisora(emisoraDao.getIdEmisora(listaEmisorasPanel.getSelecionarEmisora()));
                } else {
                    mainUI = new ReproducirEmisoraPanel(this, emisoraDao.getIdEmisora(listaEmisorasPanel.getSelecionarEmisora()));
                    mainUI.setVisible(true);
                }
                listaEmisorasPanel.setVisible(false);
                break;
            case "abrirCrearEmisora":
                // Mostrar el panel de creación de emisora
                crearEmisoraPanel.setVisible(true);
                break;
            case "crearEmisora":
                // Crear una nueva emisora
                emisoraDao.crearEmisora(crearEmisoraPanel.getDatosEmi());
                listaEmisorasPanel.actualizarEmi();
                crearEmisoraPanel.setVisible(false);
                break;
            default:
                // Acciones según el tipo de componente seleccionado
                String[] data = ActionName.split(":");
                if (data[0].equals("Lista")) {
                    mainUI.setListaDeCancionesS(UUID.fromString(data[1]));
                    mainUI.actualizarListaC();
                } else if (data[0].equals("LReproduccion")) {
                    mainUI.setReproDeCancionesS(UUID.fromString(data[1]));
                    mainUI.actualizarReproC();
                } else if (data[0].equals("Emisora")) {
                    listaEmisorasPanel.setSelecionarEmisora(UUID.fromString(data[1]));
                    listaEmisorasPanel.actualizarEmi();
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    	
    }

    @Override
    public void mouseExited(MouseEvent e) {
   
    }
}
