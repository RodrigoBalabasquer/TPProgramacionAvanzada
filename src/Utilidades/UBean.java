package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import TP.Alumno;

public class UBean {
	public static ArrayList<Field> obtenerAtributos(Object object){
		Class c = object.getClass();
		
		List<Field> fields = new ArrayList<Field>();
		
		Field[] attr = c.getDeclaredFields();
		for(Field f: attr){
			fields.add(f);
		}
		
		return (ArrayList<Field>) fields;
	}
	public static void ejecutarSet(Object o, String att, Object valor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class c = o.getClass();
		System.out.println(c.getName());
		Object[] obj = new Object[1];
		obj[0] = valor;
		Method[] methods = c.getDeclaredMethods();
		String metodo = "set" + att.substring(0, 1).toUpperCase() + att.substring(1).toLowerCase();  
		System.out.println(metodo);
		System.out.println(att);
		System.out.println(valor);
		
		for(Method method: methods){
			if(method.getName().equals(metodo)){
				method.invoke(o, obj);
			}
		}
	}
	public static Object ejecutarGet(Object o, String att) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class c = o.getClass();
		Object obj = new Object();
		Method[] methods = c.getDeclaredMethods();
		String metodo = "get" + att.substring(0, 1).toUpperCase() + att.substring(1).toLowerCase();  
		for(Method method: methods){
			if(method.getName().equals(metodo)){
				return method.invoke(o, null);
			}
		}
		return null;
	}
}
