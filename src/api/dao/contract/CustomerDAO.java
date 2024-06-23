package api.dao.contract;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {
    Customer findByEmail(String query) throws DAOException, EntityCannotFoundException;
}
