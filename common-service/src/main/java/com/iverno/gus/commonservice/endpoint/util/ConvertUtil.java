package com.iverno.gus.commonservice.endpoint.util;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertUtil {

	public static Object linkedHashMapToObject(LinkedHashMap<String, Object> map, Class typeClass){
        return new ObjectMapper().convertValue(map, typeClass);
    }
}
