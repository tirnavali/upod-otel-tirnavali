package api.service.impl;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.CustomerJdbcDao;
import api.dao.impl.UserJdbcDao;
import api.dao.model.Customer;
import api.dao.model.User;
import api.service.contract.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean checkSignIn(String email, String password) {
        var dao = new CustomerJdbcDao(Customer.class);
        try {
            var customer = dao.findByEmail(email);
            if(customer.getPassword().equals(password)){
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
