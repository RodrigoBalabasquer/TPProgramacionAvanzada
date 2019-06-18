package Servicios;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Anotaciones.*;
import TP.Alumno;
import Utilidades.UBean;
import Utilidades.UConexion;


public class Consulta {
	
	public Object guardar(Object o) throws IllegalArgumentException, IllegalAccessException{
		 
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
				for(Field field: campos){
					if((Id)field.getAnnotation(Id.class) != null)
						UBean.ejecutarSet(o, field.getName(), generatedKeys.getObject(1));
				}
			}
			
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
		return o;
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
	public Object obtenerPorId(Class c,Object id)throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException{
		Object obj = c.getConstructors()[0].newInstance(null);
		Field[] campos = obj.getClass().getDeclaredFields();
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
				valorCondicion = id.toString();//field.get(o).toString();
				columnaCondicion = ((Columna)field.getAnnotation(Columna.class)).getNombreColumna();
			}
		}
		UConexion u = UConexion.getInstancia();
		Connection con = u.getConection();
		try{
			//System.out.println("SELECT "+columnas+" FROM "+tabla + " WHERE "+columnaCondicion +" = ?");
			//System.out.println(valorCondicion);
			PreparedStatement st = con.prepareStatement("SELECT "+columnas+" FROM "+tabla + " WHERE "+columnaCondicion +" = ?");
			st.setObject(1, valorCondicion);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				for(Field field: campos){
					UBean.ejecutarSet(obj, field.getName(), rs.getObject(((Columna)field.getAnnotation(Columna.class)).getNombreColumna()));
				}
			}
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
		return obj;
	}
}


