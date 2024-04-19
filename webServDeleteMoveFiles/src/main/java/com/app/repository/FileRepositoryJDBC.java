package com.app.repository;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.model.FBW300PATH;

@Repository
public class FileRepositoryJDBC {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<String> fetchDataFromAS400() {
        // Executar la consulta SQL
        List<String> results = jdbcTemplate.queryForList(
            "SELECT 'correcte' AS funcion FROM SYSIBM.SYSDUMMY1",
            String.class
        );
        return results;
    }
	
	public boolean isDataSourceConnectionValid() {
        try {
            // Executa una consulta senzilla per comprovar la connexió
            jdbcTemplate.queryForObject("SELECT 1 FROM SYSIBM.SYSDUMMY1", Integer.class);
            // Si no es llança cap excepció, la connexió és vàlida
            return true;
        } catch (Exception e) {
            // Si es produeix una excepció, la connexió és incorrecta
            return false;
        }
    }
	
	public List<FBW300PATH> getAllArgumentsStmt() {
		List<FBW300PATH> arguments= new ArrayList<>();
		jdbcTemplate.execute((Connection connection) -> {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM FBW300PATH");
				while (rs.next()) {
					FBW300PATH argument = new FBW300PATH(
				           rs.getInt("idoper"),
				           rs.getString("reference"),
				           rs.getString("typeoper"),
				           rs.getString("deletepath"),
				           rs.getString("movepath")
				           );
					arguments.add(argument);
				}
			} catch (SQLException e) {
				  throw new RuntimeException(e);
			} 
			return null;
		});
		
		return arguments;
	}

}
