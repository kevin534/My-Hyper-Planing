package com.hyperplanning.entities;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class Cours {
    private int id;
    private Enseignant enseignant;
    private Salle salle;
    private Matiere matiere;
    private Groupe groupe;
    private Date dateDebut;
    private Date dateFin;
    private String heureDebut;


    @Override
    public String toString(){
        return "Enseignant : " +this.enseignant+
                " Salle : "+this.salle.getLibelleSalle()+" Matiere : "+this.getMatiere().getLibelleMatiere()
                +" Groupe : "+this.getGroupe().getLibelleClasse()+ " Date : "+this.getDateDebut() +" Heure : "
                +this.getHeureDebut();
    }

}
