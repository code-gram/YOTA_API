package com.yash.yotaapi.util;

import java.util.Date;

public class UniqueNameGenerator {

	public static String generateUniqueName(String fullName, String competency) {
		// Get the first letter of the full name
		String[] nameParts = fullName.split(" ");
		StringBuilder nameInitials = new StringBuilder();
		for (String part : nameParts) {
			if (!part.isEmpty()) {
				nameInitials.append(part.charAt(0));
			}
		}
		// Combine the components to create a unique name
		return nameInitials.toString().toUpperCase() + "-" + competency + "-" + new Date().getTime();
		}
}
