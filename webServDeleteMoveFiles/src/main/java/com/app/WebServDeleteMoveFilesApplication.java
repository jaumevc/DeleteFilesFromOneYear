package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServDeleteMoveFilesApplication {
	
	public static void main(String[] args) throws ClassNotFoundException, LinkageError {
//		ClassUtils.forName("com.ibm.as400.access.AS400JDBCDriver", null);
		SpringApplication.run(WebServDeleteMoveFilesApplication.class, args);
		
	}

}
