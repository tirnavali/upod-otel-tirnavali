package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.impl.ReservationJdbcDao;
import api.dao.model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservationDaoTest {
    public static void main(String[] args) throws DAOException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        var dao = new ReservationJdbcDao(Reservation.class);
        dao.create(new Reservation(1,1, 100,
                formatter.parse("22-10-2024"),
                formatter.parse("24-10-2024")));


        for(var ent : dao.getAll()){
            System.out.println(ent);
        }
    }
}
