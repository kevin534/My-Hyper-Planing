package com.hyperplanning.daos;

import com.hyperplanning.entities.Utilisateur;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao extends AbstractDao<Utilisateur>{
    public UtilisateurDao() {
        super("INSERT INTO UTILISATEUR(NOM,PRENOMS,EMAIL,PASSWORD) VALUES (?,?,?,?)",
                "UPDATE PERSONNE SET NOM=?, PRENOMS=?, EMAIL=?, PASSWORD=? WHERE ID=?");
    }
    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected Utilisateur fromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Utilisateur persist(Utilisateur utilisateur) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public void update(Utilisateur utilisateur) throws DataAccessException {

    }

    @Override
    public void close() throws Exception {

    }
}
