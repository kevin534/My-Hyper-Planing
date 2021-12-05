package com.hyperplanning.entities;
import java.util.Date;
import lombok.Data;

@Data
public class Cours {
    private Enseignant enseignant;
    private Salle salle;
    private Matiere matiere;
    private Groupe groupe;
    private Date dateDebut;
    private Date dateFin;

    public static class CoursBuilder{
        private Enseignant enseignant;
        private Salle salle;
        private Matiere matiere;
        private Groupe groupe;
        private Date dateDebut;
        private Date dateFin;

        public CoursBuilder setEnseignant(Enseignant enseignant){
            this.enseignant = enseignant;
            return this;
        }

        public CoursBuilder setSalle(Salle Salle){
            this.salle = salle;
            return this;
        }

        public CoursBuilder setMatiere(Matiere matiere){
            this.matiere = matiere;
            return this;
        }

        public CoursBuilder setGroupe(Groupe groupe){
            this.groupe = groupe;
            return this;
        }

        public CoursBuilder setDateDebut(Date dateDebut){
            this.dateDebut = dateDebut;
            return this;
        }

        public CoursBuilder setDateFin(Date dateFin){
            this.dateFin = dateFin;
            return this;
        }

        public Cours build(){
            Cours cours = new Cours();
            cours.enseignant = enseignant;
            cours.matiere = matiere;
            cours.salle = salle;
            cours.groupe = groupe;
            cours.dateDebut = dateDebut;
            cours.dateFin = dateFin;
            return cours;
        }
    }
}
