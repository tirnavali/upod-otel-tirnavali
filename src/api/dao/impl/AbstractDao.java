package api.dao.impl;

import api.Utils;
import api.dao.OtelDriverManager;
import api.dao.exceptions.DAOException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDao<T> {
    private Class<T> model;
    protected Connection connection;
    static final String COL_ID ="id";
    //public static final String DELETE = "DELETE FROM "+ "TABLE_NAME" + " WHERE " + COL_ID + " = ?";
    public Connection getConnection(){
        connection = OtelDriverManager.getConnection();
        return connection;
    }

    protected AbstractDao(Class<T> clazz){
        this.model = clazz;
        Type superClass = getClass().getGenericSuperclass();
        System.out.println(superClass.getTypeName());
        //this.model = (superClass).getTypeName();
    }

    public Class<T> getClazz() {
        System.out.println("inside get clazzz");
        System.out.println(this.model.getName());
        return this.model;
    }

    public String getClazzName() {
        System.out.println("inside get clazz namez");
        System.out.println(model.getSimpleName().toLowerCase());
        return this.model.getSimpleName().toLowerCase();
        //return this.model;
    }

    public void delete(long id) throws DAOException {
        try(var conn = getConnection()){
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM " + Utils.tableConverter.get(getClazzName()) +
                     " WHERE " + COL_ID + " = ?");
            stmt.setLong(1,id);
            var result = stmt.executeUpdate();
            conn.commit();
            if(result == 0){
                throw new DAOException(getClazzName() + " cannot be deleted with id : "+ id);
            }
        } catch (SQLException e) {
            throw new DAOException(getClazzName()+" cannot be deleted ", e);
        }
    }


    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException se) {
            System.out.println("Exception closing Connection: " + se);
        }
    }
}
