package co.edu.uco.nose.crosscuting.helper;

public class TextHelper {
	
	private static final String EMPTY = "";
	
	private TextHelper() {
	}
	
	public static String getEmpty() {
		return EMPTY;
	}
	
	public static String getDefault(final String value) {
		return ObjectHelper.getDefaultIfNull(value, getDefault());
	}
	
	public static String getDefaultWithTrim(final String value) {
		return getDefault(value).trim();
	}
	
	public static String getDefault() {
	    return getDefault();
	}
	
}
