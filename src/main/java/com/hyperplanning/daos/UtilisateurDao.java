package com.hyperplanning.daos;

import com.hyperplanning.entities.Utilisateur;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao extends AbstractDao<Utilisateur>{
    public UtilisateurDao() {
        super("INSERT INTO UTILISATEURS(NOM,PRENOMS,EMAIL,PASSWORD) VALUES (?,?,?,?)",
                "UPDATE PERSONNE SET NOM=?, PRENOMS=?, EMAIL=?, PASSWORD=? WHERE ID=?");
    }
    @Override
    public String getTableName() {
        return "UTILISATEURS";
    }

    @Override
    protected Utilisateur fromResultSet(ResultSet resultSet) throws SQLException {
        return Utilisateur.builder()
                .id(resultSet.getInt("id"))
                .nom(resultSet.getString("nom"))
                .prenoms(resultSet.getString("prenoms"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }


    public Utilisateur persist(String nom, String prenoms, String email, String password) throws DataAccessException, NotFoundException{
        try {
            persistPS.setString(1, nom);
            persistPS.setString(2, prenoms);
            persistPS.setString(3, email);
            persistPS.setString(4, password);
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getLocalizedMessage());
        }
        return super.persist();
    }
    @Override
    public Utilisateur persist(Utilisateur utilisateur) throws NotFoundException, DataAccessException {
        return persist(utilisateur.getNom(),utilisateur.getPrenoms(),utilisateur.getEmail(),utilisateur.getPassword());
    }

    @Override
    public void update(Utilisateur utilisateur) throws DataAccessException {
        try{
            updatePS.setString(2,utilisateur.getNom());
            updatePS.setString(3,utilisateur.getPrenoms());
            updatePS.setString(4,utilisateur.getEmail());
            updatePS.setString(5,utilisateur.getPassword());

        } catch (SQLException e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
        super.update();
    }

    @Override
    public void close() throws Exception {

    }
}
