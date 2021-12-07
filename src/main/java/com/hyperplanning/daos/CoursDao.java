package com.hyperplanning.daos;

import com.hyperplanning.entities.Cours;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoursDao extends AbstractDao<Cours>{
    public CoursDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Cours fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Cours persist(Cours cours) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Cours> persist(List<Cours> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Cours cours) throws DataAccessException {

    }

    @Override
    public void remove(Cours cours) throws DataAccessException {
        super.remove(cours);
    }

    @Override
    public void close() throws Exception {

    }
}
