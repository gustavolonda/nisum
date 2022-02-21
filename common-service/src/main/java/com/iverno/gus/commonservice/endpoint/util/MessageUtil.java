package com.iverno.gus.commonservice.endpoint.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageUtil {
	private static MessageSource messageResource;
	
	public static String getMessageFromResource(String property){
        return messageResource.getMessage(property, null,Locale.getDefault());
    }
	
	public static String getMessageFromResourceByCode(String code){
        return  getMessageFromResource( "iverno.gus.utils.message.exception."+ code);
    }
}
