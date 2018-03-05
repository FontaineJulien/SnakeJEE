package com.julfon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.julfon.beans.Article;
import com.julfon.beans.User;

public class ArticleDAO implements ArticleDAOInterface {
	
	DAOFactory factory_dao;
	
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM Article a JOIN Player p ON p.idPlayer = a.idPlayer ORDER BY datePost DESC;";
	
	public ArticleDAO(DAOFactory factory) {
		this.factory_dao = factory;
	}

	@Override
	public Vector<Article> find() throws DAOException {
		Vector<Article> articleList = new Vector<Article>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_ALL_ARTICLES, false);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				Article a = map(result);
				articleList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return articleList;
	}
	
	private Article map(ResultSet result) throws SQLException {
		Article a = new Article();
		User u = new User();
		
		u.setUsername(result.getString("username"));
		u.setEmail(result.getString("mailaddress"));
		u.setId(result.getLong("idplayer"));
		u.setPassword(result.getString("password"));
		u.setCredit(result.getShort("credit"));
		u.setIsAdmin(result.getShort("isadmin"));
		
		a.setAuthor(u);
		a.setDate(result.getDate("datepost"));
		a.setTitle(result.getString("title"));
		a.setContent(result.getString("content"));
		
		return a;
	}

}
