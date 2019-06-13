package TP;
import Anotaciones.*;

@Tabla(getNombreTabla = "alumnos")
public class Alumno {
	
	@Id
	@Columna(getNombreColumna = "idalumno")
	public Long idalumno;
	
	@Columna(getNombreColumna = "nombre")
	public String nombre;
	
	@Columna(getNombreColumna = "apellido")
	public String apellido;
	
	@Columna(getNombreColumna = "edad")
	public Integer edad;
	
	@Columna(getNombreColumna = "idsexo")
	public Integer sexo;
	
	public Long getIdalumno() {
		return idalumno;
	}
	public void setIdalumno(Long idalumno) {
		this.idalumno = idalumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Integer getSexo() {
		return sexo;
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	public String toString(){
		return this.idalumno +" "+this.nombre +" "+this.apellido +" "+ this.edad+ " "+this.sexo;
	}
}
