package com.julfon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.julfon.beans.User;

import com.julfon.dao.DAOMisceleanous;

public class UserDAO implements UserDAOInterface {
	
	protected DAOFactory factory_dao;
	
	private static final String SELECT_BY_USERNAME = "SELECT * FROM Player WHERE UserName=?;";
	private static final String INSERT_USER = "INSERT INTO Player (UserName,PassWord,MailAddress,Credit,isAdmin) VALUES (?,?,?,?,?);";
	private static final String SELECT_ALL_USERS = "SELECT * FROM Player;";
	private static final String DELETE_BY_ID = "DELETE FROM Player WHERE idPlayer=?;";
	
	
	public UserDAO(DAOFactory factory_dao) {
		this.factory_dao = factory_dao;
	}

	@Override
	public void create(User user) throws DAOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, INSERT_USER, false, user.getUsername(), user.getPassword(), user.getEmail(),user.getCredit(),user.getIsAdmin());
			
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
	
	public Vector<User> find() throws DAOException {
		Vector<User> userList = new Vector<User>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_ALL_USERS, false);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				User u = map(result);
				userList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return userList;
	}
	
	public void delete(short id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, DELETE_BY_ID, false, id);
			
			statement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}
	}
	
	private User map(ResultSet result) throws SQLException {
		User u = new User();
		
		u.setUsername(result.getString("username"));
		u.setEmail(result.getString("mailaddress"));
		u.setId(result.getLong("idplayer"));
		u.setPassword(result.getString("password"));
		u.setCredit(result.getShort("credit"));
		u.setIsAdmin(result.getShort("isadmin"));
		
		return u;
	
	}

}
