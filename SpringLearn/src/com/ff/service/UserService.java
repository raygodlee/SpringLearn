package com.ff.service;

import com.ff.dao.UserDAO;
import com.ff.dao.impl.UserDAOImpl;
import com.ff.model.User;

public class UserService {
	private UserDAO userDAO = new UserDAOImpl();
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void addUser(User u){
		this.userDAO.save(u);
	}
}
