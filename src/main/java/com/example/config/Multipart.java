package com.example.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import javax.xml.crypto.Data;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class Multipart {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(1048576L * 1024));
        factory.setMaxRequestSize(DataSize.ofBytes(1048576L * 1024 * 10));
        return factory.createMultipartConfig();
    }
}
