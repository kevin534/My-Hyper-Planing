package com.hyperplanning.daos;

import com.hyperplanning.entities.Utilisateur;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao extends AbstractDao<Utilisateur>{
    public UtilisateurDao() {
        super("INSERT INTO UTILISATEURS(NOM,PRENOMS,EMAIL,PASSWORD,ROLE) VALUES (?,?,?,?,?)",
                "UPDATE UTILISATEURS SET NOM=?, PRENOMS=?, EMAIL=?, PASSWORD=? WHERE ID=?");
    }
    @Override
    public String getTableName() {
        return "UTILISATEURS";
    }

    @Override
    public Utilisateur fromResultSet(ResultSet resultSet)  {
        Utilisateur utilisateur = null;
        try {
            utilisateur = Utilisateur.builder()
                    .id(resultSet.getInt("id"))
                    .nom(resultSet.getString("nom"))
                    .prenoms(resultSet.getString("prenoms"))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .role(resultSet.getString("role"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }


    public Utilisateur persist(String nom, String prenoms, String email, String password, String role) throws DataAccessException, NotFoundException{
        try {
            persistPS.setString(1, nom);
            persistPS.setString(2, prenoms);
            persistPS.setString(3, email);
            persistPS.setString(4, password);
            persistPS.setString(5, role);
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getLocalizedMessage());
        }
        return super.persist();
    }
    @Override
    public Utilisateur persist(Utilisateur utilisateur) throws NotFoundException, DataAccessException {
        return persist(utilisateur.getNom(),utilisateur.getPrenoms(),utilisateur.getEmail(),utilisateur.getPassword(), utilisateur.getRole());

    }

    @Override
    public void update(Utilisateur utilisateur) throws DataAccessException {
        try{
            updatePS.setString(1,utilisateur.getNom());
            updatePS.setString(2,utilisateur.getPrenoms());
            updatePS.setString(3,utilisateur.getEmail());
            updatePS.setString(4,utilisateur.getPassword());
            updatePS.setInt(5,utilisateur.getId());

        } catch (SQLException e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
        super.update();
    }

    @Override
    public void remove(int id) throws DataAccessException {

    }


    @Override
    public void close() throws Exception {

    }
}
