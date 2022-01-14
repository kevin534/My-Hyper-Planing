package com.hyperplanning.daos;

import com.hyperplanning.entities.Etudiant;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EtudiantDao extends AbstractDao<Etudiant>{
    public EtudiantDao() {
        super("INSERT INTO ETUDIANTS(ID,IDGROUPECLASSE) VALUES (?,?)",
                "UPDATE ETUDIANTS SET ID=?, IDGROUPECLASSE=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "ETUDIANTS";
    }

    @Override
    public Etudiant fromResultSet(ResultSet resultSet) throws SQLException {
        Etudiant etudiant =  null;
        try (GroupeDao groupeDao = new GroupeDao();){
            etudiant = Etudiant.builder()
                    .groupeClasse(groupeDao.find(resultSet.getInt("idGroupeClasse")).orElse(null))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }

        return etudiant;
    }

    public ResultSet test(){
        ResultSet rs = null;

        try(   Statement statement = connection.createStatement();) {

            rs = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id " +
                    "INNER JOIN presence ON etudiants.id = presence.id ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;


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
            //updateEtudiant
    }

    @Override
    public void remove(int id) throws DataAccessException {

    }

    @Override
    public void remove(Etudiant etudiant) throws DataAccessException {
        super.remove(etudiant);
    }

    @Override
    public void close() throws Exception {
            //close
    }
}
