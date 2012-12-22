package bio4j.common.utils;

public class StringUtl {
	public static boolean isNullOrEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static String appendStr(String line, String str, String delimiter) {
		if (isNullOrEmpty(line))
			line = ((str == null) ? "" : str);
		else
			line += delimiter + ((str == null) ? "" : str);
		return line;
	}

	public static boolean compareStrings(String str1, String str2, Boolean ignoreCase) {
		if ((str1 == null) && (str2 == null))
			return true;
		else if ((str1 == null) || (str2 == null))
			return false;
		else {
			if(ignoreCase)
				return str1.equalsIgnoreCase(str2);
			else
				return str1.equals(str2); 
		}
	}
}