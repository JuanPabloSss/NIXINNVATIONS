package co.edu.unbosque.model.persistance;

import java.io.*;
import java.util.*;

import co.edu.unbosque.model.CancionDTO;

public class CancionDAO {

	private String filec;

	private String file;

	private RegistroRadio ruta;

	public CancionDAO(String file) {
		this.filec = file;

		this.ruta = new RegistroRadio(file);

		this.file = "/Canciones.txt";
	}

	public boolean crearCancion(CancionDTO cancion) {

		String entry = cancion.getId() + ";" + cancion.getNombre() + ";" + cancion.getArtista() + ";"
				+ cancion.getGenero() + ";"+ cancion.getArchivo() + ";";

		return this.ruta.escribirRegistro(entry, this.file);
	}

	public CancionDTO getIdCancion(UUID id) {
		ArrayList<CancionDTO> canciones = this.listaCanciones("cualquiera");

		for (CancionDTO cancion : canciones) {
			if (cancion.getId().equals(id)) {
				return cancion;
			}
		}
		
		return null;
	}

	public boolean eliminarCancion(UUID id) {

		ArrayList<CancionDTO> canciones = listaCanciones("todas");

		this.ruta.eliminarRegistro(this.file);

		canciones.forEach((cancion) -> {
			if (id != cancion.getId()) {

				String entry = cancion.getId() + ";" + cancion.getNombre() + ";" + cancion.getArtista() + ";"
						+ cancion.getGenero() + ";"+ cancion.getArchivo() + ";";

				ruta.escribirRegistro(entry, this.file);

			}
		});

		return true;
	}

	public ArrayList<CancionDTO> listaCanciones(String genero) {

		ArrayList<CancionDTO> cancion = new ArrayList<CancionDTO>();

		String line = "";

		File f = new File(this.filec + this.file);

		try {

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			int i = 0;

			while (line != null) {

				CancionDTO song = new CancionDTO();

				line = br.readLine();

				if (line != null) {

					String[] data = line.split(";");
					song.setId(UUID.fromString(data[0]));
					song.setNombre(data[1]);
					song.setArtista(data[2]);
					song.setGenero(data[3]);
					song.setArchivo(data[4]);

					if (genero == "any") {

						song.setActive(true);

					} else {

						song.setActive(data[3].toLowerCase().equals(genero.toLowerCase()));

					}

					cancion.add(song);

					i++;

				}

			}

			fr.close();
		} catch (IOException e) {
			System.out.print(e);
		}

		return cancion;
	}

}
