package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.CustomerJdbcDao;
import api.dao.model.Customer;

public class CustomerDaoTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var dao = new CustomerJdbcDao(Customer.class);
        var custo = dao.findByEmail("sln@gmail.com");
        System.out.println("Founded customer :" + custo);
        var customers = dao.getAll();
        //dao.create(new Customer("Sercan", "TIRNAVALI", "sercantirnavali@live.com", "Ser18Sel", "05558833410"));
        //dao.create(new Customer("Selin", "TIRNAVALI", "sln@gmail.com", "Sel18Ser", "05558873410"));
//        dao.create(new Customer("Asil", "TIRNAVALI", "asl@gmail.com", "Sel18Ser", ""));
//        System.out.println(dao.getAll());
//        var asil = dao.find(3);
//        asil.setPhone("5556667788");
//        dao.update(asil);
//        System.out.println(dao.getAll());
//        dao.delete(3);

        System.out.println(dao.getAll());
    }
}
