package Servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;

import Utilidades.UConexion;

public class Consulta {
	
	public void guardar(Object o) throws IllegalArgumentException, IllegalAccessException{
		 
		Field[] campos = o.getClass().getDeclaredFields();
		
		String valores = "";
		for(Field field: campos){
			if(valores =="")
				valores += "'"+field.get(o)+"'";
			else
				valores +=",'"+field.get(o)+"'";
		}
		String tabla = o.getClass().getSimpleName().toLowerCase()+"s";
		
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("INSERT into "+ tabla +" values ("+valores+")");
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	} 
}
