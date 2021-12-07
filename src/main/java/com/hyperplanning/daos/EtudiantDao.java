package com.hyperplanning.daos;

import com.hyperplanning.entities.Etudiant;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EtudiantDao extends AbstractDao<Etudiant>{
    public EtudiantDao(String persistPS, String updatePS) {
        super(persistPS, updatePS);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Etudiant fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Etudiant persist(Etudiant etudiant) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Etudiant> persist(List<Etudiant> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Etudiant etudiant) throws DataAccessException {

    }

    @Override
    public void remove(Etudiant etudiant) throws DataAccessException {
        super.remove(etudiant);
    }

    @Override
    public void close() throws Exception {

    }
}
