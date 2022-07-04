package model;

import dao.UserDAO;

public class UserLogic {
	public boolean execute(User user) {
		return new UserDAO().findUser(user);
	}
}
