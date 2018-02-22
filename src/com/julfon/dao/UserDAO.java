package com.julfon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.julfon.beans.User;

import com.julfon.dao.DAOMisceleanous;

public class UserDAO implements UserDAOInterface {
	
	protected DAOFactory factory_dao;
	
	private static final String SELECT_BY_USERNAME = "SELECT * FROM user WHERE username=?;";
	private static final String INSERT_USER = "INSERT INTO user (username,email,password) VALUES (?,?,?);";
	
	public UserDAO(DAOFactory factory_dao) {
		this.factory_dao = factory_dao;
	}

	@Override
	public void create(User user) throws DAOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, INSERT_USER, false, user.getUsername(), user.getEmail(), user.getPassword());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}

	}

	@Override
	public User find(String username) throws DAOException {
		
		User u = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_BY_USERNAME, false, username);
			
			result = statement.executeQuery();
			
			if(result.next()) {
				u = map(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return u;
	}
	
	private User map(ResultSet result) throws SQLException {
		User u = new User();
		
		u.setUsername(result.getString("username"));
		u.setEmail(result.getString("email"));
		u.setId(result.getLong("id"));
		u.setPassword(result.getString("password"));
		
		return u;
	
	}

}
