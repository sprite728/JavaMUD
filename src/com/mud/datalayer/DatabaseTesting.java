package com.mud.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

public class DatabaseTesting {
	public static void main() {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		//TODO: Create database MudTest and table Testing
		//CREATE TABLE IF NOT EXISTS Testing(Id INT) ENGINE=InnoDB;
		String dbName = "MudTest";
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		String user = "root";
		String password = "root";

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO Testing(Id) VALUES(?)");
			for (int i = 1; i <= 1000; i++) {
				st.setInt(1, i * 2);
				st.executeUpdate();
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseTesting.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseTesting.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
}
