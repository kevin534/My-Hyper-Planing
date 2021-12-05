package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;

@Data
public class Etudiant extends Utilisateur{
    private Groupe groupeClasse;

    public static class EtudiantBuilder extends UtilisateurBuilder{
        private Groupe groupeClasse;
        public EtudiantBuilder setGroupeClasse(Groupe groupeClasse){
            this.groupeClasse = groupeClasse;
            return this;
        }

        public Etudiant build(){
            Etudiant etudiant = new Etudiant();
            etudiant.groupeClasse = groupeClasse;
            return etudiant;
        }
    }
}
