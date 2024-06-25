package api.dao.impl;

import api.dao.contract.RoomDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomJdbcDao extends AbstractDao<Room> implements RoomDAO {
    private static final String TABLE_NAME = "rooms";
    private static final String COL_OTEL_ID = "otelId";
    private static final String COL_NAME = "name";
    private static final String COL_CAPACITY = "capacity";
    private static final String COL_PRICE = "dailyPrice";
    private static final String COL_EX_SPEC = "extraSpeciality";
    private static final String COL_COMMON_ROOM_SPEC_ID = "commonRoomSpecialityId";

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_OTEL_ID+","+COL_NAME+","+ COL_CAPACITY + "," + COL_PRICE + "," +
            COL_EX_SPEC + "," + COL_COMMON_ROOM_SPEC_ID +") VALUES (?,?,?,?,?,?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_OTEL_ID + " = ?," +
            COL_NAME + " = ?," +
            COL_CAPACITY + " = ?, " +
            COL_PRICE + " = ?, " +
            COL_EX_SPEC + " = ?, " +
            COL_COMMON_ROOM_SPEC_ID + " = ? " +

            "WHERE " +
            COL_ID +" = ?";

    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public RoomJdbcDao(Class clazz) {
        super(clazz);
    }

    @Override
    public void create(Room room) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);
            stmt.setLong(1, room.getOtelId());
            stmt.setString(2, room.getName());
            stmt.setInt(3, room.getCapacity());
            stmt.setDouble(4, room.getDailyPrice());
            stmt.setString(5, room.getExtraSpeciality());
            stmt.setLong(6, room.getCommonRoomSpecialityId());

            if(stmt.executeUpdate() != 1){
                throw new DAOException("Error while saving room");
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException("Room cannot be created! ",e);
        }
    }

    @Override
    public void update(Room room) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setLong(1, room.getOtelId() );
            stmt.setString(2, room.getName() );
            stmt.setInt(3, room.getCapacity() );
            stmt.setDouble(4, room.getDailyPrice() );
            stmt.setString(5, room.getExtraSpeciality() );
            stmt.setLong(6, room.getCommonRoomSpecialityId());
            stmt.setLong(7, room.getId());
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
//    public Room find(long id) throws DAOException, EntityCannotFoundException {
//        Room foundedRoom = null;
//        try(var conn = getConnection()){
//            PreparedStatement stmt = conn.prepareStatement(FIND);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                var roomId = rs.getLong(COL_ID);
//                var otelId = rs.getLong(COL_OTEL_ID);
//                var name = rs.getString(COL_NAME);
//                var capacity = rs.getInt(COL_CAPACITY);
//                var price = rs.getDouble(COL_PRICE);
//                var extraSpec = rs.getString(COL_EX_SPEC);
//                var commonSpecId = rs.getLong(COL_COMMON_ROOM_SPEC_ID);
//                foundedRoom = new Room(roomId, name, otelId, capacity, price, extraSpec, commonSpecId);
//
//            }
//            if(foundedRoom == null){
//                throw new EntityCannotFoundException(TABLE_NAME, String.valueOf(id));
//            }
//            return foundedRoom;
//
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//    }

    @Override
    public List<Room> getAll() throws DAOException {
        List<Room> rooms = new ArrayList<Room>();
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_NAME);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt(COL_ID));
                room.setOtelId(rs.getInt(COL_OTEL_ID));
                room.setName(rs.getString(COL_NAME));
                room.setCapacity(rs.getInt(COL_CAPACITY));
                room.setDailyPrice(rs.getDouble(COL_PRICE));
                room.setExtraSpeciality(rs.getString(COL_EX_SPEC));
                room.setCommonRoomSpecialityId(rs.getLong(COL_COMMON_ROOM_SPEC_ID));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getAll in " + TABLE_NAME, e);
        }
        return rooms;
    }
}
