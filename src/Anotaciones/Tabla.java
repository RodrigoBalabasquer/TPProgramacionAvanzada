package Anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //El retention class guarda cuando se crea la clase y el source cuando se compila
public @interface Tabla {
	public String getNombreTabla()default "alumnos";
}
