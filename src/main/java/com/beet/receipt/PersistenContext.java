package com.beet.receipt;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PersistenContext {

	@Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        
        log.debug("driver: {}, JdbcUrl: {}, username:{}, password:{}", 
        		env.getRequiredProperty("spring.datasource.driver-class-name"), 
        		env.getRequiredProperty("spring.datasource.url"),
        		env.getRequiredProperty("spring.datasource.username"),
        		env.getRequiredProperty("spring.datasource.password"));
        
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
 
        return new HikariDataSource(dataSourceConfig);
    }
}
