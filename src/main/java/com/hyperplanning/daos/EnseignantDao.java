package com.hyperplanning.daos;

import com.hyperplanning.entities.Enseignant;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnseignantDao extends AbstractDao<Enseignant> {
    public EnseignantDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Enseignant fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Enseignant persist(Enseignant enseignant) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Enseignant> persist(List<Enseignant> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Enseignant enseignant) throws DataAccessException {

    }

    @Override
    public void remove(Enseignant enseignant) throws DataAccessException {
        super.remove(enseignant);
    }

    @Override
    public void close() throws Exception {

    }
}
