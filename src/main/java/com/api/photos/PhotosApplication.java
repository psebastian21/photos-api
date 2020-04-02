package com.api.photos;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.api.photos.controller.ControllerExceptionHandler;
import com.api.photos.exception.DataSourceConfigException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication
public class PhotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotosApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
    JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
        } catch (IllegalStateException | PropertyVetoException ex) {
            throw new DataSourceConfigException(
                    "error while setting the driver class name in the datasource", ex);
        }
        ds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
        ds.setUser(env.getRequiredProperty("jdbc.username"));
        ds.setPassword(env.getRequiredProperty("jdbc.password"));
        ds.setAcquireIncrement(env.getRequiredProperty("c3p0.acquire_increment", Integer.class));
        ds.setMinPoolSize(env.getRequiredProperty("c3p0.min_size", Integer.class));
        ds.setMaxPoolSize(env.getRequiredProperty("c3p0.max_size", Integer.class));
        ds.setMaxIdleTime(env.getRequiredProperty("c3p0.max_idle_time", Integer.class));
        ds.setUnreturnedConnectionTimeout(env.getRequiredProperty("c3p0.unreturned_connection_timeout", Integer.class));
        return ds;
    }
	
	@Bean
	Logger controllerAdvicelogger() {
		return LoggerFactory.getLogger(ControllerExceptionHandler.class);
	}

}
