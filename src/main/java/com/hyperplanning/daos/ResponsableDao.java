package com.hyperplanning.daos;

import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResponsableDao extends AbstractDao<ResponsableDao> {
    public ResponsableDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected ResponsableDao fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public ResponsableDao persist(ResponsableDao responsableDao) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<ResponsableDao> persist(List<ResponsableDao> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(ResponsableDao responsableDao) throws DataAccessException {

    }

    @Override
    public void remove(ResponsableDao responsableDao) throws DataAccessException {
        super.remove(responsableDao);
    }

    @Override
    public void close() throws Exception {

    }
}
