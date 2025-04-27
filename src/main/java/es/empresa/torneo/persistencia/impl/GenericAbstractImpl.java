package es.empresa.torneo.persistencia.impl;

import es.empresa.torneo.persistencia.config.MysqlConnector;

import java.sql.Connection;

public abstract class GenericAbstractImpl {

    protected Connection connection;
    protected String sql;
    protected int rowCount;

    public GenericAbstractImpl() {
        this.connection = MysqlConnector.getInstance().getConnection();
    }
}
