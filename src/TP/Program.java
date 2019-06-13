package TP;

import java.sql.Connection;
import java.sql.Statement;
import java.lang.reflect.Field;
import Servicios.Consulta;
import Utilidades.Item;
import Utilidades.UBean;
import Utilidades.UConexion;
import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class Program {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		Consulta cons = new Consulta();
		Alumno al = new Alumno();
		al.apellido = "qweqwe";
		al.idalumno = 5L;//Tipo long
		al.sexo = 2;
		al.edad = 200;
		al.nombre = "cosme";
		
		try {
			cons.guardar(al);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
		
		/*UConexion u = UConexion.getInstancia();
		
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("INSERT into alumnos values (null,'pepe','pepe',1,1)");
			con.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}*/
		
		/*Item item = new Item();
		try{
			List<Field> lista = UBean.obtenerAtributos(item);
			for(Field field: lista){
				System.out.println(field.getName());
			}
			
			UBean.ejecutarSet(item, "Valor1", 10);
			UBean.ejecutarSet(item, "Valor2", "fasdasd");
			
			
			System.out.println(UBean.ejecutarGet(item, "valor1"));
			System.out.println(UBean.ejecutarGet(item, "valor2"));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}*/
		
	}

}
