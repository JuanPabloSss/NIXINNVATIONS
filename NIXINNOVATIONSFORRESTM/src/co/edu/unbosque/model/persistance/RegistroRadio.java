package co.edu.unbosque.model.persistance;

import java.io.*;

public class RegistroRadio {
	
	private String file;
	
	public RegistroRadio(String file) {
		this.file = file;
	}
	
	public void eliminarRegistro(String fileName) {
		
		File f = new File(this.file+fileName);
		
		f.delete();
		
	}
	
	public boolean escribirRegistro(String entry, String fileName) {
		File f = new File(this.file+fileName);
		
		try {
			
			if(!f.exists()) {
				
				f.createNewFile();
				
			}
			
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(entry);
			
			fw.close();
			
		} catch (IOException e) {
			
			System.out.println(e);
			
			return false;
		
		} 
		
		return true;

	}

}
