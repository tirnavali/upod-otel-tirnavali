package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.ReservationJdbcDao;
import api.dao.model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ReservationDaoTest {
    public static void main(String[] args) throws DAOException, ParseException, EntityCannotFoundException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        var dao = new ReservationJdbcDao(Reservation.class);
        dao.create(new Reservation(1,1, 100,
                formatter.parse("22-10-2024"),
                formatter.parse("24-10-2024")));

        var ent = dao.find(2);
        ent.setRoomId(2);
        dao.update(ent);

        for(var enty : dao.getAll()){
            System.out.println(enty);
        }
    }
}
