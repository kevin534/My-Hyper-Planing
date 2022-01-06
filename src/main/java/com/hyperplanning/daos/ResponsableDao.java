package com.hyperplanning.daos;

import com.hyperplanning.entities.Responsable;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponsableDao extends AbstractDao<Responsable> {
    public ResponsableDao() {
        super("INSERT INTO RESPONSABLES(ID,IDGROUPECLASSE) VALUES (?,?)",
                "UPDATE RESPONSABLES SET ID=?, IDGROUPECLASSE=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "RESPONSABLES";
    }

    @Override
    public Responsable fromResultSet(ResultSet resultSet) throws SQLException {
        Responsable responsable =  null;
        try (GroupeDao groupeDao = new GroupeDao();){
            responsable = Responsable.builder()
                    .groupeClasse(groupeDao.find(resultSet.getInt("groupeClasse")).orElse(null))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responsable;
    }

    @Override
    public Responsable persist(Responsable responsable) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public void remove(Responsable responsable) throws DataAccessException {
        super.remove(responsable);
    }

    @Override
    public void close() throws Exception {

    }



    @Override
    public void update(Responsable responsable) throws DataAccessException {

    }
}
