package service.imp;

import dao.Blog;
import dao.User;
import exception.DuplicateBlogException;
import exception.NoSuchBlogException;
import jdbc.MySqlConnector;
import service.BlogService;
import service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService {

    private static Connection connection;
    private static UserService userService = new UserServiceImpl();


    static {
        try {
            connection=MySqlConnector.getConnection();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
           }

    @Override
    public List<Blog> getAll() throws SQLException {
        List<Blog> blogs = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM manyy.blogs")) {
            while (result.next()) {
                blogs.add(new Blog(result.getInt("id"), result.getString("name")));
            }
            return blogs;
        }
    }


    @Override
    public Blog getBlogById(int id) throws SQLException, NoSuchBlogException {
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM manyy.blogs WHERE id = ?")) {
            statement.setInt(1, id);
           result = statement.executeQuery();
            if (result.next()) {
                return new Blog(result.getInt("id"), result.getString("name"));
            } else throw new NoSuchBlogException("No blog with id : " + id);
        } finally {
            result.close();
        }
    }


    @Override
    public void createBlog(Blog blog) throws SQLException, DuplicateBlogException {
        if(isExists(blog.getId())){
            throw new DuplicateBlogException("Blog with id : " + blog.getId() + " already exists!");
        } else {
            System.out.println("Creating user with id : " + blog.getId());
            userService.createUser(new User(blog.getId(),"user : " + blog.getName()));
            System.out.println("Creating blog with id : " + blog.getId());
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO manyy.blogs (id, name) VALUES (?, ?)")) {
                statement.setInt(1,blog.getId());
                statement.setString(2, blog.getName());
                statement.execute();
            }
        }
    }


    private  boolean isExists(int blogId) throws SQLException {
        boolean flag =false;
        for(Blog blog:getAll()){
            flag=blog.getId()==blogId;
        }
        return  flag;
    }
}
