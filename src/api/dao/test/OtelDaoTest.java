package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.OtelJdbcDao;
import api.dao.model.Otel;

public class OtelDaoTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {

        var dao = new OtelJdbcDao( Otel.class);
        //dao.create(new Otel("Test butik otel"));
        System.out.println(dao.getAll());
        //dao.delete(4);
        var foundedOtel = dao.find(3);
        foundedOtel.setName("New test name");
        dao.update(foundedOtel);

        System.out.println("Find ---");
        System.out.println(dao.find(3));
        dao.delete(4);
        System.out.println();
        //System.out.println(dao.find(1));

        System.out.println("---");
        dao.create(new Otel("Test butik otel"));
        var otels = dao.getAll();
        for (Otel u: otels) {
            System.out.println(u);
        }
    }
}
