package api.dao.impl;

import api.dao.contract.CustomerDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.Customer;
import api.dao.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerJdbcDao extends AbstractDao<Customer> implements CustomerDAO {
    private static final String TABLE_NAME = "customers";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_PHONE = "phone";

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_EMAIL+","+COL_PASSWORD+","+ COL_NAME + "," + COL_SURNAME + "," +
        COL_PHONE +") VALUES (?,?,?,?,?)";
    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String FIND_BY_EMAIL = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_EMAIL + "  = ?";
    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_EMAIL + " = ?," +
            COL_PASSWORD + " = ?," +
            COL_NAME + " = ?, " +
            COL_SURNAME + " = ?, " +
            COL_PHONE + " = ? " +

            "WHERE " +
            COL_ID +" = ?";

    public CustomerJdbcDao(Class clazz) {
        super(clazz);
    }

    @Override
    public void create(Customer customer) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getName());
            stmt.setString(4, customer.getSurname());
            stmt.setString(5, customer.getPhone());

            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving customer");
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException("Customer cannot be created! ",e);
        }
    }

    @Override
    public void update(Customer customer) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, customer.getEmail() );
            stmt.setString(2, customer.getPassword() );
            stmt.setString(3, customer.getName() );
            stmt.setString(4, customer.getSurname() );
            stmt.setString(5, customer.getPhone() );
            stmt.setLong(6, customer.getId());
            var affectedRow = stmt.executeUpdate();
            // conn.commit();
            if(affectedRow == 0){
                throw new DAOException("Update can not be done");
            }
        } catch (SQLException e) {
            throw new DAOException("Update cannot be done ", e);
        }
    }

//    @Override
//    public Customer find(long id) throws DAOException, EntityCannotFoundException {
//        Customer customer = null;
//        try(var conn = getConnection()){
//            PreparedStatement stmt = conn.prepareStatement(FIND);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                var userId = rs.getLong(COL_ID);
//                var userEmail = rs.getString(COL_EMAIL);
//                var userPass = rs.getString(COL_PASSWORD);
//                var name = rs.getString(COL_NAME);
//                var surname = rs.getString(COL_SURNAME);
//                var phone = rs.getString(COL_PHONE);
//                customer = new Customer(userId, name, surname,userEmail,userPass,phone);
//
//            }
//            if(customer == null){
//                throw new EntityCannotFoundException(TABLE_NAME, String.valueOf(id));
//            }
//            return customer;
//
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//
//    }

    @Override
    public List<Customer> getAll() throws DAOException {
        List<Customer> customers = new ArrayList<Customer>();
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_NAME);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt(COL_ID));
                customer.setEmail(rs.getString(COL_EMAIL));
                customer.setPassword(rs.getString(COL_PASSWORD));
                customer.setName(rs.getString(COL_NAME));
                customer.setSurname(rs.getString(COL_SURNAME));
                customer.setPhone(rs.getString(COL_PHONE));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getAll in " + TABLE_NAME, e);
        }
        return customers;
    }


    @Override
    public Customer findByEmail(String query) throws DAOException, EntityCannotFoundException {
        Customer customer = null;
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(FIND_BY_EMAIL);
            stmt.setString(1, query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                var userId = rs.getLong(COL_ID);
                var userEmail = rs.getString(COL_EMAIL);
                var userPass = rs.getString(COL_PASSWORD);
                var name = rs.getString(COL_NAME);
                var surname = rs.getString(COL_SURNAME);
                var phone = rs.getString(COL_PHONE);
                customer = new Customer(userId, name, surname,userEmail,userPass,phone);

            }
            if(customer == null){
                throw new EntityCannotFoundException(TABLE_NAME, query);
            }
            return customer;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
