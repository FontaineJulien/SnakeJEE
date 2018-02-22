package com.julfon.dao;

import com.julfon.beans.User;

public interface UserDAOInterface {
	void create(User user) throws DAOException;
	User find(String username) throws DAOException;
}
