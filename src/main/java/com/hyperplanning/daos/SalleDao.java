package com.hyperplanning.daos;

import com.hyperplanning.entities.Salle;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SalleDao extends AbstractDao<Salle>{
    public SalleDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Salle fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Salle persist(Salle salle) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Salle> persist(List<Salle> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Salle salle) throws DataAccessException {

    }

    @Override
    public void remove(Salle salle) throws DataAccessException {
        super.remove(salle);
    }

    @Override
    public void close() throws Exception {

    }
}
