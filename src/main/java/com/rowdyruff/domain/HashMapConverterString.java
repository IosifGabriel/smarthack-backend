package com.rowdyruff.domain;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapConverterString implements AttributeConverter<Map<String, String>, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
    @Override
    public String convertToDatabaseColumn(Map<String, String> customerInfo) {
 
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON writing error");
            e.printStackTrace();
        }
 
        return customerInfoJson;
    }
 
    @Override
    public Map<String, String> convertToEntityAttribute(String customerInfoJSON) {
 
    	Map<String, String> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
        	System.out.println("JSON reading error");
        	e.printStackTrace();
        }
 
        return customerInfo;
    }
 
}
