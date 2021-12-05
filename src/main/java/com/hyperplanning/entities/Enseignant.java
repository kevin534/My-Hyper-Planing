package com.hyperplanning.entities;
import lombok.Data;
import java.util.List;

@Data
public class Enseignant extends Utilisateur{
    private List<Matiere> matiere;

    public static class EnseignantBuilder extends UtilisateurBuilder{
        private List<Matiere> matiere;

        public EnseignantBuilder setMatiere(List<Matiere> matiere){
            this.matiere = matiere;
            return this;
        }

        public Enseignant build(){
            Enseignant enseignant = new Enseignant();
            enseignant.matiere = matiere;
            return enseignant;
        }
    }
}
