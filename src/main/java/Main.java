import dao.Blog;
import dao.User;
import exception.DuplicateBlogException;
import exception.NoSuchBlogException;
import service.BlogService;
import service.UserService;
import service.imp.BlogServiceImpl;
import service.imp.UserServiceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {

    private static BlogService blogService = new BlogServiceImpl();
    private static UserService userService = new UserServiceImpl();

    private static final Logger log =Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) throws SQLException, NoSuchBlogException, DuplicateBlogException {





/*        System.out.println(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);

        System.out.println(" ------------- GET by ID ----------------");
        System.out.println(blogService.getBlogById(45));*/

      /*  System.out.println(" ---------- CREATE User --------------");
        userService.createUser(new User(6, "New"));*/

      //  System.out.println(" ---------- CREATE Blog with User --------------");
       // blogService.createBlog(new Blog(5, "Window5"));
        log.info("WORKKK ");
        log.warning("Pez");


    }
}
