package com.hyperplanning.daos;

import com.hyperplanning.entities.Cours;
import com.hyperplanning.entities.Matiere;
import com.hyperplanning.entities.Salle;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.security.spec.ECField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatiereDao extends AbstractDao<Matiere>{

    public MatiereDao() {
        super("INSERT INTO MATIERES(LIBELLEMATIERE) VALUES (?)",
                "UPDATE MATIERES SET LIBELLEMATIERE=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "MATIERES";
    }

    @Override
    public Matiere fromResultSet(ResultSet resultSet) {
        Matiere matiere = null;
        try {
            matiere =  Matiere.builder()
                    .codeMatiere(resultSet.getInt("id"))
                    .libelleMatiere(resultSet.getString("libelleMatiere"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  matiere;
    }

    public List<Matiere> getMatiere(){

        ResultSet rs = null;
        List<Matiere> matiere = new ArrayList<>();

        try ( Statement statement = connection.createStatement();){

            rs = statement.executeQuery("SELECT * FROM MATIERES");
            while (rs.next()) {
                matiere.add(Matiere.builder()
                        .codeMatiere(rs.getInt("id"))
                        .libelleMatiere(rs.getString("libelleMatiere"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return matiere;


    }


    @Override
    public Matiere persist(Matiere matiere) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Matiere> persist(List<Matiere> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Matiere matiere) throws DataAccessException {

    }

    @Override
    public void remove(int id) throws DataAccessException {

    }

    @Override
    public void remove(Matiere matiere) throws DataAccessException {
        super.remove(matiere);
    }

    @Override
    public void close() throws Exception {

    }
}
