package api.dao.contract;

import api.DbContext;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;

import java.util.List;

public interface GenericDAO<T> extends AutoCloseable {
    public void create(T t) throws DAOException;
    public void delete(long id) throws DAOException;
    public void update(T t) throws DAOException;
    public T find(long id) throws DAOException, EntityCannotFoundException;
    public List<T> getAll() throws DAOException;
}
