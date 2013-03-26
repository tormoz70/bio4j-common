package bio4j.common.utils;

import java.util.ArrayList;
import java.util.List;

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

	public static String[] split(String str, String[] delimiters) {
		if (!isNullOrEmpty(str)) {
			if ((delimiters != null) && (delimiters.length > 0)) {
				String line = str;
				String dlmtr = null;
				if (delimiters.length > 1) {
					final String csDlmtrPG = "#inner_pg_delimeter_str#";
					for (String delimeter : delimiters)
						line = line.replace(delimeter, csDlmtrPG);
				} else
					dlmtr = delimiters[0];
				List<String> lst = new ArrayList<String>();
				int item_bgn = 0;
				while (item_bgn <= line.length()) {
					String line2Add = "";
					int dlmtr_pos = line.indexOf(dlmtr, item_bgn);
					if (dlmtr_pos == -1)
						dlmtr_pos = line.length();
					line2Add = line.substring(item_bgn, dlmtr_pos);
					lst.add(line2Add);
					item_bgn += line2Add.length() + dlmtr.length();
				}
				return lst.toArray(new String[lst.size()]);
			} else
				return new String[] { str };
		} else
			return new String[] {};
	}

	public static String[] split(String str, String delimiter) {
		return split(str, new String[] { delimiter });
	}

	public static boolean compareStrings(String str1, String str2, Boolean ignoreCase) {
		if ((str1 == null) && (str2 == null))
			return true;
		else if ((str1 == null) || (str2 == null))
			return false;
		else {
			if (ignoreCase)
				return str1.equalsIgnoreCase(str2);
			else
				return str1.equals(str2);
		}
	}
}