package com.rowdyruff.domain;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapConverter implements AttributeConverter<Map<Integer, String>, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
    @Override
    public String convertToDatabaseColumn(Map<Integer, String> customerInfo) {
 
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
    public Map<Integer, String> convertToEntityAttribute(String customerInfoJSON) {
 
    	Map<Integer, String> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
        	System.out.println("JSON reading error");
        	e.printStackTrace();
        }
 
        return customerInfo;
    }
 
}
