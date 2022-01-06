package com.hyperplanning.daos;

import com.hyperplanning.entities.Salle;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Salle fromResultSet(ResultSet resultSet) throws SQLException {
        return Salle.builder()
                .codeSalle(resultSet.getInt("id"))
                .libelleSalle(resultSet.getString("libelleSalle"))
                .batiment(resultSet.getString("batimentSalle"))
                .build();
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

    }

    @Override
    public void remove(Salle salle) throws DataAccessException {
        super.remove(salle);
    }

    @Override
    public void close() throws Exception {

    }
}
