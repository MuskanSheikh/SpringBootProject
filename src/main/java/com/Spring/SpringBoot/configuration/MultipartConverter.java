package com.Spring.SpringBoot.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Component
public class MultipartConverter implements Converter<MultipartFile,String> {
    @Override
    public String convert(MultipartFile source) {
        try{
            return new String(source.getBytes(), StandardCharsets.UTF_8);
        }catch(Exception e)
        {
            throw new RuntimeException("Fail to convert multipartfile to string",e);
        }
    }
}
