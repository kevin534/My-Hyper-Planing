package com.hyperplanning.daos;

import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.Salle;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalleDao extends AbstractDao<Salle>{
    public SalleDao() {
        super("INSERT INTO SALLES(LIBELLESALLE,BATIMENT) VALUES (?,?)",
                "UPDATE SALLES SET LIBELLESALLE=?, BATIMENT=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "SALLES";
    }

    @Override
    public Salle fromResultSet(ResultSet resultSet) {
        Salle salle = null;
        try {
            salle =  Salle.builder()
                    .codeSalle(resultSet.getInt("id"))
                    .libelleSalle(resultSet.getString("libelleSalle"))
                    .batiment(resultSet.getString("batimentSalle"))
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
        return salle;
    }
    public List<Salle> getSalle(){
        ResultSet rs = null;
        List<Salle> salle = new ArrayList<>();

        try(Connection connection = DBCPDataSource.getConnection();
            Statement statement = connection.createStatement();){

            rs = statement.executeQuery("SELECT * FROM Salles");
            while (rs.next()) {
                salle.add(Salle.builder()
                        .codeSalle(rs.getInt("id"))
                        .libelleSalle(rs.getString("libelleSalle"))
                        .batiment(rs.getString("batimentSalle"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return salle;


    }
    @Override
    public Salle persist(Salle salle) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Salle> persist(List<Salle> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Salle salle) throws DataAccessException {
            //update
    }

    @Override
    public void remove(int id) throws DataAccessException {

    }


    @Override
    public void close() throws Exception {

    }
}
