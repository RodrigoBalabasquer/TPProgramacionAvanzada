package Servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Anotaciones.*;
import TP.Alumno;
import Utilidades.UBean;
import Utilidades.UConexion;


public class Consulta {
	
	public void guardar(Object o) throws IllegalArgumentException, IllegalAccessException{
		 
		Class c = o.getClass();
		Field[] campos = o.getClass().getDeclaredFields();
		
		String valores = "";
		String columnas = "";
		for(Field field: campos){
			if(valores ==""){
				if(field.getAnnotation(Id.class) == null)
					valores += "'"+field.get(o)+"'";
			}
			else{
				if(field.getAnnotation(Id.class) == null)
					valores +=",'"+field.get(o)+"'";
			}
		}
		String tabla = ((Tabla)c.getAnnotation(Tabla.class)).getNombreTabla();
		
		for(Field field: campos){
			if(columnas ==""){
				if(field.getAnnotation(Id.class) == null)
					columnas += "("+((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
			else{
				if(field.getAnnotation(Id.class) == null)
					columnas +=","+((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
		}
		columnas +=")";
		
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("INSERT into "+ tabla + columnas +" values ("+valores+")",Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = st.getGeneratedKeys();
			if (generatedKeys.next()) {
			         System.out.println(generatedKeys.getInt(1));
			}
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	} 
	public void modificar(Object o)throws IllegalArgumentException, IllegalAccessException{
		Class c = o.getClass();
		Field[] campos = o.getClass().getDeclaredFields();
		String query = "";
		String valorCondicion = "";
		String columnaCondicion = "";
		String tabla = ((Tabla)c.getAnnotation(Tabla.class)).getNombreTabla();
		
		for(Field field: campos){
			if(field.getAnnotation(Id.class) != null){
				valorCondicion = field.get(o).toString();
				columnaCondicion = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
			else{
				if(query == "")
					query = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna() + "='" +field.get(o).toString()+"'";
				else
					query += ","+((Columna)field.getAnnotation(Columna.class)).getNombreColumna() + "='" +field.get(o).toString()+"'";
			}
		}
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("UPDATE "+ tabla + " SET "+query +" WHERE "+columnaCondicion + "="+valorCondicion);
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	}
	public void eliminar(Object o)throws IllegalArgumentException, IllegalAccessException{
		Class c = o.getClass();
		Field[] campos = o.getClass().getDeclaredFields();
		String tabla = ((Tabla)c.getAnnotation(Tabla.class)).getNombreTabla();
		String valorCondicion = "";
		String columnaCondicion = "";
		
		for(Field field: campos){
			if(field.getAnnotation(Id.class) != null){
				valorCondicion = field.get(o).toString();
				columnaCondicion = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
		}
		
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("DELETE FROM "+ tabla + " WHERE "+columnaCondicion + "="+valorCondicion);
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	}
	public Object obtenerPorId(Class c,Object o)throws IllegalArgumentException, IllegalAccessException{
		Field[] campos = o.getClass().getDeclaredFields();
		String columnas = "";
		String valorCondicion = "";
		String columnaCondicion = "";
		String tabla = ((Tabla)c.getAnnotation(Tabla.class)).getNombreTabla();
		
		for(Field field: campos){
			if(columnas == "")
				columnas = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			else
				columnas += ","+((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			
			if(field.getAnnotation(Id.class) != null){
				valorCondicion = field.get(o).toString();
				columnaCondicion = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
		}
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		try{
			System.out.println("SELECT "+columnas+" FROM "+tabla + " WHERE "+columnaCondicion +" = ?");
			PreparedStatement st = con.prepareStatement("SELECT "+columnas+" FROM "+tabla + " WHERE "+columnaCondicion +" = ?");
			st.setLong(1, Long.valueOf(valorCondicion));
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				for(Field field: campos){
					UBean.ejecutarSet(o, field.getName(), rs.getObject(((Columna)field.getAnnotation(Columna.class)).getNombreColumna()));
				}
				/*System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
				System.out.println(rs.getObject(4));
				System.out.println(rs.getObject(5));*/
			}
			con.close();
			System.out.println(o.toString());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
		return o;
	}
}


