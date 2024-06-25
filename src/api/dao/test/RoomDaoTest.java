package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.RoomJdbcDao;
import api.dao.model.Room;

public class RoomDaoTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var dao = new RoomJdbcDao(Room.class);
        var room = dao.find(1);
        System.out.println(room);
//        System.out.println(dao.getAll());
//        dao.create(new Room("AZ01",1,2,1800.0,"Covid steril oda", 2));
//        dao.create(new Room("AZ02",1,2,1800.0,"Covid steril oda", 2));
//        dao.create(new Room("AZ03",1,2,1800.0,"Covid steril oda", 2));
//        dao.create(new Room("AZ04",1,2,1800.0,"Covid steril oda", 2));
//        for (var room: dao.getAll()) {
//            System.out.println(room);
//        }
//        var room = dao.find(1);
//        room.setExtraSpeciality("Çocuk yataklı oda");
//        dao.update(room);
//        for (var rm: dao.getAll()) {
//            System.out.println(rm);
//        }
//        dao.delete(6);
//        for (var rm: dao.getAll()) {
//            System.out.println(rm);
//        }

    }
}
