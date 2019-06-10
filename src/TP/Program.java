package TP;

import java.sql.Connection;
import java.sql.Statement;

import Servicios.Consulta;
import Utilidades.UConexion;

public class Program {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		/*UConexion u = UConexion.getInstancia();
		
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("INSERT into alumnos values (null,'pepe','pepe',1,1)");
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}*/
		
		Consulta cons = new Consulta();
		Alumno al = new Alumno();
		al.apellido = "asdasd";
		al.idalumno = 1;
		al.idsexo = 1;
		al.edad = 20;
		al.nombre = "sadsasd";
		
		try {
			cons.guardar(al);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	}

}
