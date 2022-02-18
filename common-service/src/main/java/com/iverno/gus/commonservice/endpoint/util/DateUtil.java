package com.iverno.gus.commonservice.endpoint.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {

	public static Date today() {
		// getting the system date
		return new Date();
	}
	public static Timestamp now() {
		return new Timestamp(today().getTime());
		
	}
	
	
}
