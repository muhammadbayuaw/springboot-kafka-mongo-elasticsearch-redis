package com.bagayugu.dummy.util;

/**
 * @author Muhammad Bayu Agusto
 * Util class for response messages and codes
 */

public class ResponseUtil {
	public static class Message{
		public static final String SUCCESS = "Success";
		public static final String NOT_FOUND = "Data not found";
		public static final String NO_CONTENT = "No content";
	}

	public static class Code{
		public static final String SUCCESS = "200";
		public static final String NOT_FOUND = "404";
		public static final String NO_CONTENT = "204";
	}
}
