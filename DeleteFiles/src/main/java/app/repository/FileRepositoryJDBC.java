package app.repository;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import app.beans.FBW300PATH;

@Repository
public class FileRepositoryJDBC {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	public FBW300PATH getActionByReference(String reference) {
		// Amb JDBC el nom de les colulmnes ha de coincidir exactament
		FBW300PATH action = jdbcTemplate.queryForObject(
				"SELECT * FROM interven2.FBW300PATH WHERE reference=?",
				new Object[] { reference }, (rs, rowNum) -> new FBW300PATH(
						rs.getInt("idoper"),
						rs.getString("reference"), 
						rs.getString("rootpath"), 
						rs.getString("movepath"),
						rs.getInt("limitdays")
						));

		return action;
	}

	public int getDaysByReference(String reference) {
	    String query = "SELECT limitdays FROM interven2.FBW300PATH WHERE reference=?";
	    Integer result = jdbcTemplate.queryForObject(query, Integer.class, reference);
	    return result != null ? result : 0;
	}
	
//	public List<FBW300PATH> getAllArgumentsStmt() {
//		List<FBW300PATH> arguments= new ArrayList<>();
//		jdbcTemplate.execute((Connection connection) -> {
//			try {
//				Statement statement = connection.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT * FROM interven2.FBW300PATH");
//				while (rs.next()) {
//					FBW300PATH argument = new FBW300PATH(
//				           rs.getInt("idoper"),
//				           rs.getString("reference"),
//				           rs.getString("rootpath"),
//				           rs.getString("movepath"),
//				           rs.getInt("limitdays")
//				           );
//					arguments.add(argument);
//				}
//			} catch (SQLException e) {
//				  throw new RuntimeException(e);
//			} 
//			return null;
//		});
//		
//		return arguments;
//	}

}
