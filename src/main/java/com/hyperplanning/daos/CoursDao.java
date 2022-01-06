package com.hyperplanning.daos;

import com.hyperplanning.entities.*;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursDao extends AbstractDao<Cours>{
    public CoursDao() {
        super("INSERT INTO COURS(IDENSEIGNANTS,CODESALLE,GROUPECLASSE,CODEMATIERE, DATEDEBUT, DATEFIN,HEUREDEBUT) VALUES (?,?,?,?,?,?,?)",
                "UPDATE COURS SET IDENSEIGNANTS=?, CODESALLE=?, GROUPECLASSE=?, CODEMATIERE=?,DATEDEBUT=?,DATEFIN=?,HEUREDEBUT=? WHERE ID=?");

    }

    @Override
    public String getTableName() {
        return "COURS";
    }

    @Override
    public Cours fromResultSet(ResultSet resultSet) throws SQLException {
        Cours cours =  null;
        try (EnseignantDao enseignantDao = new EnseignantDao();
             SalleDao salleDao = new SalleDao();
             GroupeDao groupeDao = new GroupeDao();
             MatiereDao matiereDao = new MatiereDao()){
            cours = Cours.builder()
                    .id(resultSet.getInt("id"))
                    .enseignant(enseignantDao.find(resultSet.getInt("id")).orElse(null))
                    .salle(salleDao.find(resultSet.getInt("codeSalle")).orElse(null))
                    .groupe(groupeDao.find(resultSet.getInt("groupeClasse")).orElse(null))
                    .matiere(matiereDao.find(resultSet.getInt("codeMatiere")).orElse(null))
                    .dateDebut(resultSet.getDate("dateDebut"))
                    .dateFin(resultSet.getDate("dateFin"))
                    .heureDebut(resultSet.getString("heureDebut"))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cours;

    }

    @Override
    public Cours persist(Cours cours) throws NotFoundException, DataAccessException {
        return persist(cours.getEnseignant(),cours.getSalle(),cours.getGroupe(),
                cours.getMatiere(),cours.getDateDebut(),cours.getDateFin(),cours.getHeureDebut());
    }


    public Cours persist(Enseignant enseignant, Salle salle, Groupe groupe, Matiere matiere, Date dateDebut, Date dateFin,String heureDebut ) throws NotFoundException, DataAccessException {
        try {
            persistPS.setInt(1, enseignant.getId());
            persistPS.setInt(2, salle.getCodeSalle());
            persistPS.setInt(3, groupe.getGroupeClasse());
            persistPS.setInt(4, matiere.getCodeMatiere());
            persistPS.setDate(5, dateDebut);
            persistPS.setDate(6, dateFin);
            persistPS.setString(7, heureDebut);
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getLocalizedMessage());
        }
        return super.persist();

    }

    @Override
    public void update(Cours cours) throws DataAccessException {
        try{
            updatePS.setInt(1,cours.getEnseignant().getId());
            updatePS.setInt(2,cours.getSalle().getCodeSalle());
            updatePS.setInt(3,cours.getGroupe().getGroupeClasse());
            updatePS.setInt(4,cours.getMatiere().getCodeMatiere());
            updatePS.setDate(5,cours.getDateDebut());
            updatePS.setDate(6,cours.getDateFin());
            updatePS.setString(7,cours.getHeureDebut());
            updatePS.setInt(8,cours.getId());

        } catch (SQLException e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
        super.update();
    }

    @Override
    public void remove(Cours cours) throws DataAccessException {
        super.remove(cours);
    }

    @Override
    public void close() throws Exception {

    }
}
