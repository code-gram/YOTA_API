package com.yash.yotaapi.util;
 
import java.util.Date;
 
public class UniqueNameGenerator {
 
	public static String generateUniqueName(String technology, String month, String year) {
		// Get the first letter of the full name
		technology = (String) technology.subSequence(0,3);
		month = (String) month.subSequence(0,8);
		return technology.toUpperCase() + "-" + month.toUpperCase() + "-" + year + "-001";
		}
}