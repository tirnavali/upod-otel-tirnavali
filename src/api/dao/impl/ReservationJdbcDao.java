package api.dao.impl;

import api.dao.contract.ReservationDAO;
import api.dao.exceptions.DAOException;
import api.dao.model.AbstractModel;
import api.dao.model.Reservation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationJdbcDao extends AbstractDao<Reservation> implements ReservationDAO {
    private static final String TABLE_NAME = "reservations";
    private static final String COL_CUSTOMER_ID = "customerId";
    private static final String COL_ROOM_ID = "roomId";
    private static final String COL_FIXED_PRICE = "fixedPrice";
    private static final String COL_ENTRANCE_DATE = "entranceDate";
    private static final String COL_LEAVE_DATE = "leaveDate";
    private static final String COL_CHECK_IN_DATE = "checkInDate";
    private static final String COL_CHECK_OUT_DATE = "checkOutDate";
    public ReservationJdbcDao(Class<Reservation> clazz) {
        super(clazz);
    }

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
        "("+COL_CUSTOMER_ID+","+COL_ROOM_ID+","+ COL_FIXED_PRICE + "," + COL_ENTRANCE_DATE + "," +
            COL_LEAVE_DATE +") VALUES (?,?,?,?,?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_CUSTOMER_ID + " = ?," +
            COL_ROOM_ID + " = ?," +
            COL_FIXED_PRICE + " = ?, " +
            COL_ENTRANCE_DATE + " = ?, " +
            COL_LEAVE_DATE + " = ?, " +
            COL_CHECK_IN_DATE + " = ? ," +
            COL_CHECK_OUT_DATE + " = ? " +

            "WHERE " +
            COL_ID +" = ?";

    @Override
    public void create(Reservation reservation) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);
            stmt.setLong(1, reservation.getCustomerId());
            stmt.setLong(2, reservation.getRoomId());
            stmt.setDouble(3, reservation.getFixedPrice());
            stmt.setDate(4, new Date(reservation.getEntranceDate().getTime()));
            stmt.setDate(5, new Date(reservation.getLeaveDate().getTime()));
            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving " + getClazzName());
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException(getClazzName() + " cannot be created! ",e);
        }
    }

    @Override
    public void update(Reservation reservation) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setLong(1, reservation.getCustomerId() );
            stmt.setLong(2, reservation.getRoomId() );
            stmt.setDouble(3, reservation.getFixedPrice() );
            stmt.setDate(4, new Date(reservation.getEntranceDate().getTime()));
            stmt.setDate(5, new Date(reservation.getLeaveDate().getTime()) );
            stmt.setDate(6, reservation.getCheckInDate() != null ? new Date(reservation.getCheckInDate().getTime()) : null);
            stmt.setDate(7, reservation.getCheckOutDate() != null ? new Date(reservation.getCheckOutDate().getTime()) : null);
            stmt.setLong(8, reservation.getId());
            var affectedRow = stmt.executeUpdate();
            // conn.commit();
            if(affectedRow == 0){
                throw new DAOException("Update can not be done in " + getClazzName() );
            }
        } catch (SQLException e) {
            throw new DAOException("Update cannot be done in " + getClazzName(), e);
        }
    }
}
