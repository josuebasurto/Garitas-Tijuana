package com.josuebasurto.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class stringHelper {
	
	public static final String FormatterString = "dd.MM.yyyy, HH:mm";
	
	public static String DateToString(Date fecha){		
		SimpleDateFormat formatter = new SimpleDateFormat(FormatterString);
		String cadena = formatter.format(fecha);
		return cadena;
	}
	
	public static Date StringToDate(String fecha){
		SimpleDateFormat formatter = new SimpleDateFormat(FormatterString);
		Date fechaRespuesta = new Date();
		try {
			fechaRespuesta = formatter.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaRespuesta;
	}
	
}
