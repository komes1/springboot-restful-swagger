package com.EMRService.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplate {

    @Bean(name = "jdbcTemplate_CISDB")
    public org.springframework.jdbc.core.JdbcTemplate jdbcTemplate_CISDB(@Qualifier("DB1") final DataSource ds) {
        return new org.springframework.jdbc.core.JdbcTemplate(ds);
    }

    @Bean(name = "jdbcTemplate_CISDB_DATA")
    public org.springframework.jdbc.core.JdbcTemplate jdbcTemplate_CISDB_DATA(@Qualifier("DB2") final DataSource ds) {
        return new org.springframework.jdbc.core.JdbcTemplate(ds);
    }
}
