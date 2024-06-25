package api.dao.impl;

import api.dao.contract.CommonRoomSpecialtyDAO;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import api.dao.model.CommonRoomSpeciality;
import api.dao.model.Otel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonRoomSpecialityJdbcDao extends AbstractDao<CommonRoomSpeciality> implements CommonRoomSpecialtyDAO {
    private static final String TABLE_NAME = "common_room_specialities";
    private static final String COL_SPEC = "speciality";

    private static final String CREATE = "INSERT INTO " + TABLE_NAME +
            "("+COL_SPEC+") VALUES (?)";
    public static final String FIND = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_ID + " = ?";
    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET " +
            COL_SPEC + " = ? " +
            "WHERE " +
            COL_ID +" = ?";
    public CommonRoomSpecialityJdbcDao(Class clazz) {
        super(clazz);
    }

    @Override
    public void create(CommonRoomSpeciality commonRoomSpeciality) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CREATE);
            conn.setAutoCommit(false);
            stmt.setString(1, commonRoomSpeciality.getSpeciality());

            if(stmt.executeUpdate() != 1){
                super.getClazzName();
                throw new DAOException("Error while saving otel");
            };
            conn.commit();

        } catch (SQLException e) {
            throw new DAOException("Otel cannot be created! ",e);
        }
    }

    @Override
    public void update(CommonRoomSpeciality commonRoomSpeciality) throws DAOException {
        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, commonRoomSpeciality.getSpeciality() );
            stmt.setLong(2, commonRoomSpeciality.getId());
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
//    public CommonRoomSpeciality find(long id) throws DAOException, EntityCannotFoundException {
//        CommonRoomSpeciality foundedSpec = null;
//        try(var conn = getConnection()){
//            PreparedStatement stmt = conn.prepareStatement(FIND);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                var spec_id = rs.getLong(COL_ID);
//                var spec = rs.getString(COL_SPEC);
//                foundedSpec = new CommonRoomSpeciality(spec_id, spec);
//
//            }
//            if(foundedSpec == null){
//                throw new EntityCannotFoundException(TABLE_NAME, String.valueOf(id));
//            }
//            return foundedSpec;
//
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//    }

//    @Override
//    public List<CommonRoomSpeciality> getAll() throws DAOException {
//        List<CommonRoomSpeciality> specs = new ArrayList<CommonRoomSpeciality>();
//        try(var conn = getConnection()){
//            PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_NAME);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                CommonRoomSpeciality spec = new CommonRoomSpeciality(rs.getString(COL_SPEC));
//                spec.setId(rs.getInt(COL_ID));
//                specs.add(spec);
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Error while getAll in " + TABLE_NAME, e);
//        }
//        return specs;
//    }
}
