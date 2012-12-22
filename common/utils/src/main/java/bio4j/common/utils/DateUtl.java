package bio4j.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtl {
	public static Date parse(String str, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
	        return format.parse(str);
        } catch (ParseException e) {
        	return null;
        }
	}
}
