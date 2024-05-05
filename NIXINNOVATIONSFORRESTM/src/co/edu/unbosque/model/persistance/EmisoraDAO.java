package co.edu.unbosque.model.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import co.edu.unbosque.model.EmisoraDTO;

public class EmisoraDAO {

	private String filec;

	private String file;

	private RegistroRadio ruta;

	public EmisoraDAO(String file) {
		this.filec = file;

		this.ruta = new RegistroRadio(file);

		this.file = "/Emisora.txt";
	}

	public boolean crearEmisora(String[] values) {
		
		String entry = UUID.randomUUID() + ";" + values[0]+ ";" + values[1] + ";" + values[2] + ";";

		return this.ruta.escribirRegistro(entry, this.file);
	}
	
	public EmisoraDTO getIdEmisora(UUID id) {
		ArrayList<EmisoraDTO> emisora = this.listaEmisoras();

		for (EmisoraDTO show : emisora) {
			if (show.getId().equals(id)) {
				return show;
			}
		}
		
		return null;
	}

	public boolean eliminarEmisora() {

		return true;
	}

	public ArrayList<EmisoraDTO> listaEmisoras() {

		ArrayList<EmisoraDTO> emosora = new ArrayList<EmisoraDTO>();

		String line = "";

		File f = new File(this.filec + this.file);

		try {

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			int i = 0;

			while (line != null) {

				line = br.readLine();

				if (line != null) {

					String[] data = line.split(";");

					emosora.add(new EmisoraDTO(UUID.fromString(data[0]), data[1], data[2], data[3]));

					i++;

				}

			}

			fr.close();
		} catch (IOException e) {
			System.out.print(e);
		}

		return emosora;
	}

}
