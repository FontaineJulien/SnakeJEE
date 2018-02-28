package com.julfon.dao;

import java.util.Vector;

import com.julfon.beans.User;

public interface UserDAOInterface {
	void create(User user) throws DAOException;
	User find(String username) throws DAOException;
	User find(short idPlayer) throws DAOException;
	Vector<User> find() throws DAOException;
	void update(long idPlayer, String username, String email, short isAdmin);
	void delete(long id) throws DAOException;
}
