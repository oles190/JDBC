package service.imp;

import dao.User;
import jdbc.MySqlConnector;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private static Connection connection;

    static {
        try {
            connection = MySqlConnector.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void createUser(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO manyy.users(id,full_name) VALUES(?, ?)")) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFullName());
            statement.execute();
        }
    }


//    @Override
//    public void createUser(User user) throws SQLException {
//    try(PreparedStatement statement=connection.prepareStatement("INSERT INTO manyy.users(id,full_name)) VALUES(?,?")){
//        statement.setInt(1,user.getId());
//        statement.setString(2,user.getFullName());
//        statement.execute();
//        }
//    }
}
