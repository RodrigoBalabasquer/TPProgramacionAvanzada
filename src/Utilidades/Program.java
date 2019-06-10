package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		/*UConexion u = UConexion.getInstancia();
		
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			System.out.println("ok");
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
