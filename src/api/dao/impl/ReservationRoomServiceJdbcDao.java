package api.dao.impl;

import api.dao.contract.ReservationRoomServiceDAO;
import api.dao.exceptions.DAOException;
import api.dao.model.ReservationRoomService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationRoomServiceJdbcDao extends AbstractDao<ReservationRoomService> implements ReservationRoomServiceDAO {
    private static final String TABLE_NAME = "reservations_room_services";
    private static final String COL_ROOM_SERVICE_ID = "roomServiceId";
    private static final String COL_RESERVATION_ID = "reservationId";
    private static final String COL_FIXED_SERVICE_PRICE = "fixedServicePrice";

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_ROOM_SERVICE_ID+","+COL_RESERVATION_ID+","+ COL_FIXED_SERVICE_PRICE +") VALUES (?,?,?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_ROOM_SERVICE_ID + " = ? ," +
            COL_RESERVATION_ID + " = ? ," +
            COL_FIXED_SERVICE_PRICE + " = ? " +
            "WHERE " +
            COL_ID +" = ?";


    public ReservationRoomServiceJdbcDao(Class<ReservationRoomService> clazz) {
        super(clazz);
    }

    @Override
    public void create(ReservationRoomService reservationRoomService) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);

            stmt.setLong(1, reservationRoomService.getRoomServiceId());
            stmt.setLong(2, reservationRoomService.getReservationId());
            stmt.setDouble(3, reservationRoomService.getFixedServicePrice());

            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving " + super.getClazzName());
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException( super.getClazzName() + " cannot be created! ",e);
        }
    }

    @Override
    public void update(ReservationRoomService reservationRoomService) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setLong(1, reservationRoomService.getRoomServiceId() );

            stmt.setLong(2, reservationRoomService.getReservationId() );
            stmt.setDouble(3, reservationRoomService.getFixedServicePrice() );
            stmt.setLong(4, reservationRoomService.getId());
            var affectedRow = stmt.executeUpdate();
            // conn.commit();
            if(affectedRow == 0){
                throw new DAOException("Update can not be done in" + getClazzName());
            }
        } catch (SQLException e) {
            throw new DAOException("Update cannot be done in" + getClazzName(), e);
        }
    }
}
