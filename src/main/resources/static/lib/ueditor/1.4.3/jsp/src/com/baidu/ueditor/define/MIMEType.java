package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;

public class MIMEType {

	public static final Map<String, String> types = new HashMap<String, String>(){{
		put( "/static/image/gif", ".gif" );
		put( "/static/image/jpeg", ".jpg" );
		put( "/static/image/jpg", ".jpg" );
		put( "/static/image/png", ".png" );
		put( "/static/image/bmp", ".bmp" );
	}};
	
	public static String getSuffix ( String mime ) {
		return MIMEType.types.get( mime );
	}
	
}
