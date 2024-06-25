package api.dao.impl;

import api.Utils;
import api.dao.OtelDriverManager;
import api.dao.exceptions.DAOException;
import api.dao.exceptions.EntityCannotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    private Class<T> model;
    private String tableName;
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
        this.tableName = Utils.tableConverter.get( getClazzName());
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

    public List<T> getAll() throws DAOException {
        List<T> objects = new ArrayList<T>();
        try(var conn = getConnection()){
            var constructor = getClazz().getDeclaredConstructor();
//            var obj = constructor.newInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from " + tableName);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            var colCount = meta.getColumnCount();
            while(rs.next()){
                var obj = constructor.newInstance();
                for (int i =1 ; i <= colCount ; i ++) {
                    var colName = meta.getColumnName(i);
                    var methodName = Utils.generateMethodNameFromColName(meta.getColumnName(i));
                    var colObj = Utils.generateTypeFromString(meta.getColumnTypeName(i));
                    var method = obj.getClass().getMethod(methodName, colObj);
                    Object rowValue = generateObjectFromResultSet(rs, colObj, colName);
                    method.invoke(obj, rowValue);
                }
                objects.add(obj);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getAll in " + tableName, e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public T find(long id) throws DAOException, EntityCannotFoundException {
        String findQuery = "SELECT * FROM "+ tableName + " WHERE " + COL_ID + " = ?";

        try(var conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(findQuery);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            var constructor = getClazz().getDeclaredConstructor();
            var ot = constructor.newInstance();

            ResultSetMetaData meta = rs.getMetaData();
            var colCount = meta.getColumnCount();
            System.out.println("Col count : " + colCount);
            var getIdMethod = ot.getClass().getMethod("getId");
            while(rs.next()){
                for (int i =1 ; i <= colCount ; i ++) {
                    System.out.println("i . is : " + i);
                    var colName = meta.getColumnName(i);
                    var methodName = Utils.generateMethodNameFromColName(meta.getColumnName(i));
                    System.out.println("Method name : " + methodName);
                    var colObj = Utils.generateTypeFromString(meta.getColumnTypeName(i));
                    System.out.println(colObj);
                    var method = ot.getClass().getMethod(methodName, colObj);
                    Object rowValue = generateObjectFromResultSet(rs, colObj, colName);
                    method.invoke(ot, rowValue);
                    getIdMethod.invoke(ot);
                    System.out.println("ot IId : " + getIdMethod.invoke(ot).toString());
                }
            }
            var getIdResult = getIdMethod.invoke(ot);
            if((long)getIdResult == 0){
                throw new EntityCannotFoundException(tableName, String.valueOf(id));
            }
            return ot;

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private Object generateObjectFromResultSet(ResultSet rs, Class colObj, String colName) throws SQLException {
        Object rowValue = new Object();
        if (colObj == int.class) {
            rowValue = rs.getInt(colName);
        } else if (colObj == String.class) {
            rowValue = rs.getString(colName);
        } else if (colObj == double.class) {
            rowValue = rs.getDouble(colName);
        } else if (colObj == java.sql.Date.class) {
            rowValue = rs.getDate(colName);
        }
        return rowValue;
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
