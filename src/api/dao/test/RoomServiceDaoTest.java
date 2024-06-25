package api.dao.test;

import api.dao.contract.RoomServiceDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.RoomServiceJdbcDao;
import api.dao.model.RoomService;

public class RoomServiceDaoTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var dao = new RoomServiceJdbcDao(RoomService.class);
//        dao.create(new RoomService("Pc with mouse", 100.3));
//        dao.delete(4);
        for(var ent : dao.getAll()){
            System.out.println(ent);
        }
        var foundedDao = dao.find(2);
        foundedDao.setPrice(200.3);
        System.out.println(foundedDao);
    }
}
