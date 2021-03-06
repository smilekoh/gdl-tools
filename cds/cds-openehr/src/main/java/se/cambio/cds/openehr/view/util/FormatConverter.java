/*
 * Created on 07-jul-2006
 *


 */
package se.cambio.cds.openehr.view.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import se.cambio.cds.util.exceptions.InternalErrorException;

/**
 * @author icorram
 *


 */
public class FormatConverter {

	private static long secondInMillis = 1000;
	private static long minuteInMillis = secondInMillis * 60;
	private static long hourInMillis = minuteInMillis * 60;
	private static long dayInMillis = hourInMillis * 24;
	private static long monthInMillis = dayInMillis * 30;
	private static long yearInMillis = dayInMillis * 365;

	public static Integer toInt(String str) throws Exception{
		try{
			return new Integer(Integer.parseInt(str.trim()));
		}catch(NumberFormatException e){
			throw new Exception(str);
		}
	}

	public static Double toDouble(String str) 
	throws NumberFormatException {
		if (str.contains(",")) {
			str = str.replace(",", ".");
		}
		return Double.parseDouble(str.trim());
	}

	public static String toComaDouble(String str) 
	throws NumberFormatException {
		if (str.contains(".")) {
			str = str.replace(".", ",");
		}
		return str;
	}

	public static Short toShort(String str){
		try{
			return new Short(Short.parseShort(str.trim()));
		}catch(NumberFormatException e){
			return new Short((short)0);
		}
	}

	public static Boolean toBoolean(String str){
		try{
			if (str.trim().equals("1")) {
				return true;
			} else {
				return false;
			}
		}catch(NumberFormatException e){
			return null;
		}
	}

	public static Short getEdad(Calendar fechaNac){
		Calendar fechaAct = Calendar.getInstance();
		boolean restaUno = false;
		if (fechaAct.get(Calendar.DAY_OF_MONTH) -
				fechaNac.get(Calendar.DAY_OF_MONTH)<0){
			restaUno = true;
		}
		if (fechaAct.get(Calendar.MONTH) -
				(fechaNac.get(Calendar.MONTH)+(restaUno?1:0))<0){
			restaUno = true;
		}else{
			restaUno=false;
		}
		return new Short((short)
				((fechaAct.get(Calendar.YEAR) - (fechaNac.get(Calendar.YEAR)+(restaUno?1:0)))));
	}

	public static String toString(Properties properties){
		String resultStr = new String();
		ArrayList<String> lista = new ArrayList<String>();
		for (Object id : properties.keySet()) lista.add((String)id);
		Collections.sort(lista);
		for (String id : lista) {
			Object value = properties.get(id);
			resultStr=resultStr+id+"="+value+"\n";
		}
		return resultStr;
	}

	public static Calendar toCalendar(Date date){
		if (date==null) {
			return null;
		}
		Calendar cDate = Calendar.getInstance();
		cDate.setTime(date);
		return cDate;
	}

	public static String toCSVString(Calendar fecha) {
		return 	
		""+fecha.get(Calendar.DAY_OF_MONTH)+
		"/"+(fecha.get(Calendar.MONTH)+1)+
		"/"+fecha.get(Calendar.YEAR);
	}

	public static Date toDate(Calendar cDate){
		if (cDate==null) {
			return null;
		}
		return cDate.getTime();
	}

	public static String toSorteableString(Calendar fecha) {
		return 	""+fecha.get(Calendar.YEAR)+
		"-"+fecha.get(Calendar.MONTH)+
		"-"+(fecha.get(Calendar.DAY_OF_MONTH)<10?"0":"")+fecha.get(Calendar.DAY_OF_MONTH);
	}

	public static String toStdMinString(Long time) {
		DateFormat dateFormat = new SimpleDateFormat("mm:ss:SSSS"); 
		return dateFormat.format(time);
	}

	public static String toStdHourString(Long time) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(time);
	}
	
	public static String toStdDiaString(Long time) {
		DateFormat dateFormat = new SimpleDateFormat("DD:HH:mm:ss"); 
		return dateFormat.format(time);
	}
	
	public static String toStdString(Calendar fecha) {
		return toStdString(fecha.getTime());
	}

	public static String toStdString(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(fecha);
	}

	public static String toStdStringWithHour(Calendar fecha) {
		return toStdStringWithHour(fecha.getTime());
	}

	public static String toStdStringWithHour(Date fecha) {
		return DateFormat.getInstance().format(fecha);
	}

	public static long anhosTranscurridosEntre(Calendar time1, Calendar time2){
		if (time1!=null && time2!=null){
			long l1 = time1.getTime().getTime();
			long l2 = time2.getTime().getTime();
			long diff = l2 - l1;
			return diff / yearInMillis;
		}else{
			return 0;
		}
	}

	public static Calendar toCalendar(String date) throws InternalErrorException{
		String delimChar = "/";
		if (date.indexOf("-")>0){
			delimChar="-";
		}
		return toCalendar(date, delimChar);
	}

	public static Calendar toCalendar(String date, String delimChar) throws InternalErrorException{
		Calendar cDate = Calendar.getInstance();
		try{
			StringTokenizer st = new StringTokenizer(date,delimChar);

			String str = null; 

			str = (String)st.nextToken();
			cDate.set(Calendar.DAY_OF_MONTH,Integer.parseInt(str));

			str = (String)st.nextToken();
			cDate.set(Calendar.MONTH,Integer.parseInt(str)-1);

			str = (String)st.nextToken();
			cDate.set(Calendar.YEAR,Integer.parseInt(str));

			return cDate;
		}catch(Exception e){
			try{
				DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
				cDate.setTime(dateFormat.parse(date));
				return cDate;
			}catch(Exception e2){
				throw new InternalErrorException(new Exception("Incorrect date: "+date));
			}
		}
	}

	
	public static long mesesTranscurridosEntre(Calendar time1, Calendar time2){
		if (time1!=null && time2!=null){
			long l1 = time1.getTime().getTime();
			long l2 = time2.getTime().getTime();
			long diff = l2 - l1;
			diff = diff % yearInMillis;
			return diff / monthInMillis;
		}else{
			return 0;
		}
	}

	/**
	 * Devuelve la cuenta de d�as descontando del n�mero de meses y a�os que 
	 * las fechas se llevan de diferencia
	 * @param time1
	 * @param time2
	 * @return los d�as entre una fecha y otra, sin contar meses y a�os
	 */
	public static long diasTranscurridosEntre(Calendar time1, Calendar time2){
		if (time1!=null && time2!=null){
			long l1 = time1.getTime().getTime();
			long l2 = time2.getTime().getTime();
			long diff = l2 - l1;
			diff = diff % yearInMillis;
			diff = diff % monthInMillis;
			return diff / dayInMillis;
		}else{
			return 0;
		}
	}

	public static boolean mismoDia(Calendar fecha1, Calendar fecha2){
		if (
				(fecha1.get(Calendar.YEAR)==fecha2.get(Calendar.YEAR)) &&
				(fecha1.get(Calendar.MONTH)==fecha2.get(Calendar.MONTH)) &&
				(fecha1.get(Calendar.DAY_OF_MONTH)==fecha2.get(Calendar.DAY_OF_MONTH))
		){
			return true;
		} else return false;
	}

	public static boolean isBirthday(Calendar fecha){
		Calendar today = Calendar.getInstance();
		if (
				(fecha.get(Calendar.MONTH)==today.get(Calendar.MONTH)) &&
				(fecha.get(Calendar.DAY_OF_MONTH)==today.get(Calendar.DAY_OF_MONTH))
		){
			return true;
		} else return false;
	}

	public static int diasDeDiferencia(Calendar fecha1, Calendar fecha2){
		long endL = fecha2.getTimeInMillis() + fecha2.getTimeZone().getOffset( fecha2.getTimeInMillis() );
		long startL = fecha1.getTimeInMillis() + fecha1.getTimeZone().getOffset( fecha1.getTimeInMillis() );
		return (int)((endL - startL) / 86400000);
		//86400000 = N�mero de milisegundos en un d�a 
	}

	public static String textoSinPuntuacionMin(String str){
		return Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 2.0/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  2.0 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *
 *  The Initial Developers of the Original Code are Iago Corbal and Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2012-2013
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */