package com.zj.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Pattern;


public class StringUtil {
	
	private static String formatTimer;
	private static final String LOCK = "lock";
	private static SimpleDateFormat format = new SimpleDateFormat(
			CommonConstant.DATE_FORMAT_YYYYMMDDHH24MMSS_3MILLIS);

	/* retrieve the only kind of format timer */
	public static String newKey() {
		synchronized (LOCK) {
			return getFormatTimerX();
		}
	}

	/**
	 * 
	 * @return
	 */
	private static String getFormatTimerX() {
		boolean flag = false;
		while (true) {
			GregorianCalendar date = new GregorianCalendar();
			String currentTimer = format.format(date.getTime());
			if (formatTimer == null) {
				formatTimer = currentTimer;
				flag = true;
			} else if (currentTimer.equals(formatTimer)) {
				// getFormatTimerX();
				continue;
			} else {
				formatTimer = currentTimer;
				flag = true;
			}
			if (flag) {
				break;
			}
		}
		return formatTimer;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getDateStringWithMillis() {
		return StringUtil.newKey();
	}
	
	/**
	 * 
	 * @param aThrowable
	 * @return
	 */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String convertEmptyToNull(String str) {
		if ("".equals(str)) {
			return null;
		}
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String convertNullToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	/**
	 * 
	 * @param numL
	 * @return
	 */
	public static String convertNullToEmpty(Long numL) {
		if (numL == null) {
			return "";
		}
		return String.valueOf(numL);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String convertEmptyToSpaceTHML(String str) {
		if (str == null || "".equals(str)) {
			return "&nbsp;";
		}
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("(\\S)+[@]{1}(\\S)+[.]{1}(\\w)+");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobilePhoneNo(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		String userPhoneRegExp = "[0]{0,1}[1-9][0-9]{10}";
		Pattern pattern = Pattern.compile(userPhoneRegExp);
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTelNo(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		String userTelRefExp = "(([0-9]{2}[\\-])([0-9]{3,4}[\\-])[0-9]{3,8})|(([0-9]{3,4}[\\-]{0,1}){0,1}[0-9]{3,8})";
		Pattern pattern = Pattern.compile(userTelRefExp);
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @param prmtvType
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getTypeClass(String prmtvType) throws Exception {
		if (prmtvType == null || "".equals(prmtvType)) {
			return null;
		}
		Class<?> clz = null;
		if ("byte".equalsIgnoreCase(prmtvType)) {
			clz = byte.class;
		} else if ("char".equalsIgnoreCase(prmtvType)) {
			clz = char.class;
		} else if ("boolean".equalsIgnoreCase(prmtvType)) {
			clz = boolean.class;
		} else if ("short".equalsIgnoreCase(prmtvType)) {
			clz = short.class;
		} else if ("int".equalsIgnoreCase(prmtvType)) {
			clz = int.class;
		} else if ("long".equalsIgnoreCase(prmtvType)) {
			clz = long.class;
		} else if ("float".equalsIgnoreCase(prmtvType)) {
			clz = float.class;
		} else if ("double".equalsIgnoreCase(prmtvType)) {
			clz = double.class;
		} else if ("byte[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[B");
		} else if ("char[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[C");
		} else if ("boolean[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[Z");
		} else if ("short[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[S");
		} else if ("int[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[I");
		} else if ("long[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[J");
		} else if ("float[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[F");
		} else if ("double[]".equalsIgnoreCase(prmtvType)) {
			clz = Class.forName("[D");
		} else if (prmtvType.endsWith("[]")) {
			String itemType = prmtvType.substring(0, prmtvType.length() - 2);
			clz = Class.forName("[L" + itemType + ";");
		} else {
			clz = Class.forName(prmtvType);
		}
		return clz;
	}
	
	/**
	 * 
	 * @param srcStr
	 * @return
	 */
	public static long getStringLen(String srcStr) {
		if (srcStr == null || "".equals(srcStr)) {
			return 0L;
		}
		char[] c = srcStr.toCharArray();
		long len = 0L;
		for (int i = 0; i < c.length; i++) {
			String binaryStr = Integer.toBinaryString(c[i]);
			if (binaryStr.length() > 8) {
				len = len + 2L;
			} else {
				len++;
			}
		}
		return len;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getRandomStringWith6BitNum() {
		Random r = new Random();
		int rr = r.nextInt(900000) + 100000;
		return String.valueOf(rr);
	}
	
	public static boolean isImg(String filename){
		String userTelRefExp = ".*\\.(?i)(jpg|gif|png|bmg|ico|jpeg)";
		Pattern pattern = Pattern.compile(userTelRefExp);
		return pattern.matcher(filename).matches();
		
	}
}
