package TP;

import java.sql.Connection;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UConexion u = UConexion.getInstancia();
		
		Connection con = u.getConection();
		
		try{
			Statement st = con.createStatement();
			st.execute("INSERT into alumnos values (null,'pepe','pepe',1,1)");
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()); 
		}
	}

}
