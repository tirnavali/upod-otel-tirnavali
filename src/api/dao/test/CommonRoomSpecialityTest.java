package api.dao.test;

import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.impl.CommonRoomSpecialityJdbcDao;
import api.dao.model.CommonRoomSpeciality;
import com.sun.tools.jconsole.JConsoleContext;

public class CommonRoomSpecialityTest {
    public static void main(String[] args) throws DAOException, EntityCannotFoundException {
        var dao = new CommonRoomSpecialityJdbcDao(CommonRoomSpeciality.class);
        var specs = dao.getAll();
        System.out.println(specs);
//        dao.create(new CommonRoomSpeciality("Wifi, tv, minibar, balkon"));
        var spec = dao.find(1);
        System.out.println(spec);
//        spec.setSpeciality("Harika deniz manzarası");
//        dao.update(spec);
//        System.out.println(dao.getAll());
//        dao.delete(4);
//        spec = dao.find(4);


//        System.out.println(dao.getAll());
    }
}
