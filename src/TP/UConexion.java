package TP;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UConexion {
	static UConexion uc;
	private Connection con= null;
	
	private UConexion(){
		this.Conectar();
	}
	public static UConexion getInstancia(){
		if(uc == null)
			uc = new UConexion();
		return uc;
	}
	private Connection Conectar(){
		
		Properties p = new Properties();
		try{
			p.load(new FileReader("framework.properties"));
			Class.forName(p.getProperty("driver"));
			String s = p.getProperty("base");
			this.con = DriverManager.getConnection(s,p.getProperty("usuario"),p.getProperty("clave"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
		
		return this.con;
	}
	public Connection getConection(){
		return this.con;
	}
}
