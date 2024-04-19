package com.app.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class DataSourceConfig {
	
//	@Bean
    public void /*DataSource*/ dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
//        dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
//       dataSource.setUrl("jdbc:db2://192.168.1.12/S06c383t");
//        dataSource.setUrl("jdbc:db2://192.168.1.12:50000/INTERVEN2");
//        dataSource.setUsername("jvalls");
//        dataSource.setPassword("$00000");

//        dataSource.setSchema("INTERVEN2");
//        return dataSource;
    }

}
