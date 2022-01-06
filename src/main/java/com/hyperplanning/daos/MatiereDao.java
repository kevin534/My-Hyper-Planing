package com.hyperplanning.daos;

import com.hyperplanning.entities.Matiere;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Matiere fromResultSet(ResultSet resultSet) throws SQLException {
        return Matiere.builder()
                .codeMatiere(resultSet.getInt("id"))
                .libelleMatiere(resultSet.getString("libelleMatiere"))
                .build();
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
    public void remove(Matiere matiere) throws DataAccessException {
        super.remove(matiere);
    }

    @Override
    public void close() throws Exception {

    }
}
