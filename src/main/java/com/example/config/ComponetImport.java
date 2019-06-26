package com.example.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@Import(FdfsClientConfig.class)
//解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ComponetImport {
    @Autowired
    private FdfsWebServer fdfsWebServer;

    @Autowired
    private Config config;

    @Bean
    void setFdfsClientUrl() {
        fdfsWebServer.setWebServerUrl(config.fdfsIpaddr.concat("/"));
    }
}
