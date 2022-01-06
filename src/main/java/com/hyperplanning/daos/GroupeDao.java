package com.hyperplanning.daos;

import com.hyperplanning.entities.Groupe;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Groupe fromResultSet(ResultSet resultSet) throws SQLException {
        return Groupe.builder()
                .groupeClasse(resultSet.getInt("id"))
                .libelleClasse(resultSet.getString("libelleClasse"))
                .build();

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
    public void remove(Groupe groupe) throws DataAccessException {
        super.remove(groupe);
    }

    @Override
    public void close() throws Exception {

    }
}
