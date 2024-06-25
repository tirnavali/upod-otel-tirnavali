package api.dao.impl;

import api.Utils;
import api.dao.contract.OtelDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.Otel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OtelJdbcDao extends AbstractDao<Otel> implements OtelDAO {
    private static final String TABLE_NAME = "otels";
    private static final String COL_NAME = "name";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_NAME+") VALUES (?)";
    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String DELETE = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_NAME + " = ? " +
            "WHERE " +
            COL_ID +" = ?";

    public OtelJdbcDao(Class clazz) {
        super(clazz);
    }

    @Override
    public void create(Otel otel) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);

            stmt.setString(1, otel.getName());

            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving " + super.getClazzName());
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException( super.getClazzName() + " cannot be created! ",e);
        }
    }


    @Override
    public void update(Otel otel) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, otel.getName() );
            stmt.setLong(2, otel.getId());
            var affectedRow = stmt.executeUpdate();
            // conn.commit();
            if(affectedRow == 0){
                throw new DAOException("Update can not be done");
            }
        } catch (SQLException e) {
            throw new DAOException("Update cannot be done ", e);
        }
    }

    @Override
    public List<Otel> getAll() throws DAOException {
        List<Otel> otels = new ArrayList<Otel>();
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_NAME);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Otel otel = new Otel(rs.getString(COL_NAME));
                otel.setId(rs.getInt(COL_ID));
                otels.add(otel);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getAll in " + TABLE_NAME, e);
        }
        return otels;
    }

}
