package api.dao.impl;

import api.dao.contract.OtelDAO;
import api.dao.exceptions.DAOException;
import api.dao.model.Otel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OtelJdbcDao extends AbstractDao<Otel> implements OtelDAO {
    private static final String TABLE_NAME = "otels";
    private static final String COL_NAME = "name";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_NAME+") VALUES (?)";
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
}