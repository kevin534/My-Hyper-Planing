package com.hyperplanning.daos;

import com.hyperplanning.entities.Enseignant;
import com.hyperplanning.entities.Matiere;
import com.hyperplanning.entities.Utilisateur;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDao extends AbstractDao<Enseignant> {
    public EnseignantDao() {
        super("INSERT INTO ENSEIGNANTS(ID,CODEMATIERE) VALUES (?,?)",
                "UPDATE ENSEIGNANTS SET ID=?, CODEMATIERE=? WHERE ID=?");
    }

    @Override
    public String getTableName() {
        return "ENSEIGNANTS";
    }

    @Override
    public Enseignant fromResultSet(ResultSet resultSet) throws SQLException {
        Enseignant enseignant = null;
        List<Matiere> mat = new ArrayList<>();
        
        try(MatiereDao matiereDao = new MatiereDao();
        UtilisateurDao utilisateurDao = new UtilisateurDao()){

            mat.add(matiereDao.find(resultSet.getInt("codeMatiere")).orElse(null));

            enseignant = Enseignant.builder()
                    .id(resultSet.getInt("id"))
                    .nom(utilisateurDao.find(resultSet.getInt("id")).orElse(null).getNom())
                    .prenoms(utilisateurDao.find(resultSet.getInt("id")).orElse(null).getPrenoms())
                    .matiere(mat)
                    .build();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return enseignant;
    }

    @Override
    public Enseignant persist(Enseignant enseignant) throws NotFoundException, DataAccessException {
        return null;
    }

    @Override
    public List<Enseignant> persist(List<Enseignant> list) throws NotFoundException, DataAccessException {
        return super.persist(list);
    }

    @Override
    public void update(Enseignant enseignant) throws DataAccessException {

    }

    @Override
    public void remove(Enseignant enseignant) throws DataAccessException {
        super.remove(enseignant);
    }

    @Override
    public void close() throws Exception {

    }
}
