package api.dao.test;

import api.dao.contract.UserDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.UserJdbcDao;
import api.dao.model.User;

public class UserDaoTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var userDao = new UserJdbcDao();
        System.out.println("Find ---");
        System.out.println(userDao.find(1));
        System.out.println(userDao.find(2));
        userDao.delete(2);
        System.out.println();
        System.out.println(userDao.find(2));

        System.out.println("---");
        userDao.create(new User("tirnavali@gmail.com", "123456"));
        var users = userDao.getAll();
        for (User u: users) {
            System.out.println(u);
        }
    }
}
