package api.dao.impl;

import api.dao.OtelDriverManager;
import api.dao.contract.GenericDAO;

import java.sql.Connection;

public abstract class AbstractDao<T> implements GenericDAO<T> {
    static final String COL_ID ="id";
    public Connection getConnection(){
        return OtelDriverManager.getConnection();
    }
}
