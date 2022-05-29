package com.taller1SM.businessdelegate;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class BusinessDelegate implements BusinessDelgateI {
	
	private final static String URL = "http://localhost:8080/api";
    private final static String PRO_URL = URL + "/products/";
    private final static String LOC_URL = URL + "/locations/";
    private final static String PCH_URL = URL + "/productcosthistory/";
    private final static String PI_URL = URL + "/productinventory/";
    
    private RestTemplate restTemplate;

    public BusinessDelegate() {
    	
        this.restTemplate = new RestTemplate();
        
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        messageConverters.add(converter);
        
        this.restTemplate.setMessageConverters(messageConverters);
    }

    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    
} //end of class
