package com.julfon.dao;

import java.util.Vector;

import com.julfon.beans.User;

public interface UserDAOInterface {
	void create(User user) throws DAOException;
	User find(String username) throws DAOException;
	Vector<User> find() throws DAOException;
	void delete(short id) throws DAOException;
}
