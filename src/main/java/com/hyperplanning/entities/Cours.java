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


}
