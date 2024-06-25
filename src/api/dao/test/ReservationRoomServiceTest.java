package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.ReservationRoomServiceJdbcDao;
import api.dao.model.ReservationRoomService;

public class ReservationRoomServiceTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var dao = new ReservationRoomServiceJdbcDao(ReservationRoomService.class);
//        dao.create(new ReservationRoomService(1,21,100));

        System.out.println(dao.find(4));

        for(var ent : dao.getAll()){
            System.out.println(ent);
        }
    }
}
