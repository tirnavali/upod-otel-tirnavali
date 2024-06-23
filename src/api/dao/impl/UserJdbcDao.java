package api.dao.impl;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.User;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "users";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_EMAIL+","+COL_PASSWORD+") VALUES (?,?)";
    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String DELETE = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
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
    public void delete(long id) throws DAOException {
        try(var conn = getConnection()){
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setLong(1,id);
            var result = stmt.executeUpdate();
            conn.commit();
            if(result == 0){
                throw new DAOException("User cannot be deleted with id : "+ id);
            }
        } catch (SQLException e) {
            throw new DAOException("User cannot be deleted ", e);
        }
    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public User find(long id) throws DAOException, EntityCannotFoundException {
        User foundedUser = null;
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(FIND);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                var userId = rs.getLong(COL_ID);
                var userEmail = rs.getString(COL_EMAIL);
                var userPass = rs.getString(COL_PASSWORD);
                foundedUser = new User(userId, userEmail, userPass);

            }
            if(foundedUser == null){
                throw new EntityCannotFoundException(TABLE_NAME, String.valueOf(id));
            }
            return foundedUser;

        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<User>();
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_NAME);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt(COL_ID));
                user.setEmail(rs.getString(COL_EMAIL));
                user.setPassword(rs.getString(COL_PASSWORD));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getAll in " + TABLE_NAME, e);
        }
        return users;
    }


    @Override
    public void close() throws Exception {

    }
}
