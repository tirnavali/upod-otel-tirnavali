package api.service.impl;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.UserJdbcDao;
import api.dao.model.User;
import api.service.contract.UserService;

public class UserServiceImpl implements UserService {


    @Override
    public boolean checkSignIn(String username, String password) {
        var usrDao = new UserJdbcDao(User.class);
        try {
            var user = usrDao.findByEmail(username);
            if(user.getPassword().equals(password)){
                return true;
            } else {
                return false;
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (EntityCannotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
