package api.dao.impl;

import api.dao.contract.UserDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.User;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao extends AbstractDao<User> implements UserDAO {
    private static final String TABLE_NAME = "users";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_EMAIL+","+COL_PASSWORD+") VALUES (?,?)";
    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    //public static final String DELETE = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_EMAIL + " = ?, " +
            COL_PASSWORD + " = ? WHERE " +
            COL_ID +" = ?";

    public UserJdbcDao(Class clazz) {
        super(clazz);
    }

    @Override
    public void create(User user) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving user");
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException("User cannot be created! ",e);
        }

    }


    @Override
    public void update(User user) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, user.getEmail() );
            stmt.setString(2, user.getPassword());
            stmt.setLong(3,user.getId());
            var affectedRow = stmt.executeUpdate();
            // conn.commit();
            if(affectedRow == 0){
                throw new DAOException("Update can not be done");
            }
        } catch (SQLException e) {
            throw new DAOException("Update cannot be done ", e);
        }
    }
}
