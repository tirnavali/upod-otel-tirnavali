package api.dao.impl;

import api.dao.contract.RoomServiceDAO;
import api.dao.exceptions.DAOException;
import api.dao.model.RoomService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomServiceJdbcDao extends AbstractDao<RoomService> implements RoomServiceDAO {
    private static final String TABLE_NAME = "room_services";
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_NAME+","+ COL_PRICE+") VALUES (?,?)";
    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_NAME + " = ? ," +
            COL_PRICE + " = ? " +
            "WHERE " +
            COL_ID +" = ?";

    public RoomServiceJdbcDao(Class<RoomService> clazz) {
        super(clazz);
    }

    @Override
    public void create(RoomService roomService) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);

            stmt.setString(1, roomService.getName());
            stmt.setDouble(2, roomService.getPrice());

            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving " + super.getClazzName());
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException( super.getClazzName() + " cannot be created! ",e);
        }
    }

    @Override
    public void update(RoomService roomService) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, roomService.getName() );
            stmt.setDouble(2, roomService.getPrice() );
            stmt.setLong(3, roomService.getId());
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
