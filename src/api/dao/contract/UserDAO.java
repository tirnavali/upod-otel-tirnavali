package api.dao.contract;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.User;

public interface UserDAO extends GenericDAO<User>{
    User findByEmail(String query) throws DAOException, EntityCannotFoundException;
}
