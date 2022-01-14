package com.hyperplanning.daos;

import com.hyperplanning.entities.Groupe;
import com.hyperplanning.entities.Salle;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao extends AbstractDao<Groupe>{
    public GroupeDao() {
        super("INSERT INTO GROUPES(LIBELLECLASSE) VALUES (?)",
                "UPDATE GROUPES SET LIBELLECLASSE=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "GROUPES";
    }

    @Override
    public Groupe fromResultSet(ResultSet resultSet) {
        Groupe groupe = null;
        try {
            groupe =  Groupe.builder()
                    .groupeClasse(resultSet.getInt("id"))
                    .libelleClasse(resultSet.getString("libelleClasse"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }

        return groupe;

    }

    public Groupe getGroupe(int id){
        ResultSet rs ;
        Groupe groupe = null;

        try (Statement statement = connection.createStatement()){

            rs = statement.executeQuery("SELECT * FROM Groupes WHERE ID ="+id);

                groupe = Groupe.builder()
                        .groupeClasse(rs.getInt("id"))
                        .libelleClasse(rs.getString("libelleClasse"))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;


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
    public void remove(int id) throws DataAccessException {

    }

    @Override
    public void remove(Groupe groupe) throws DataAccessException {
        super.remove(groupe);
    }

    @Override
    public void close() throws Exception {

    }
}
