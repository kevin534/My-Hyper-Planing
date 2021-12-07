package com.hyperplanning.daos;

import com.hyperplanning.entities.Groupe;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupeDao extends AbstractDao<Groupe>{
    public GroupeDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Groupe fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Groupe persist(Groupe groupe) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Groupe> persist(List<Groupe> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Groupe groupe) throws DataAccessException {

    }

    @Override
    public void remove(Groupe groupe) throws DataAccessException {
        super.remove(groupe);
    }

    @Override
    public void close() throws Exception {

    }
}
