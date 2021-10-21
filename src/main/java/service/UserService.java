package service;

import dao.User;

import java.sql.SQLException;

public interface UserService {

    void createUser(User user) throws SQLException;
}
