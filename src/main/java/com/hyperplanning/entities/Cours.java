package com.hyperplanning.entities;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cours {
    private Enseignant enseignant;
    private Salle salle;
    private Matiere matiere;
    private Groupe groupe;
    private Date dateDebut;
    private Date dateFin;


}
