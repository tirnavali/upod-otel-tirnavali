package api.dao.impl;

import api.dao.contract.OtelDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.Otel;
import api.dao.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtelJdbcDao extends AbstractDao implements OtelDAO {
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
                throw new DAOException("Error while saving otel");
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException("Otel cannot be created! ",e);
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
    public Otel find(long id) throws DAOException, EntityCannotFoundException {
        Otel foundedOtel = null;
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(FIND);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                var otelId = rs.getLong(COL_ID);
                var otelName = rs.getString(COL_NAME);
                foundedOtel = new Otel(otelId, otelName);

            }
            if(foundedOtel == null){
                throw new EntityCannotFoundException(TABLE_NAME, String.valueOf(id));
            }
            return foundedOtel;

        } catch (SQLException e) {
            throw new DAOException(e);
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
