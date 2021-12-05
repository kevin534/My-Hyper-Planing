package com.hyperplanning.entities;
import lombok.Data;

@Data
public class Responsable extends Utilisateur{
    private Groupe groupeClasse;

    public static class ResponsableBuilder extends UtilisateurBuilder{
        private Groupe groupeClasse;
        public ResponsableBuilder setGroupeClasse(Groupe groupeClasse){
            this.groupeClasse = groupeClasse;
            return this;
        }

        public Responsable build(){
            Responsable responsable = new Responsable();
            responsable.groupeClasse = groupeClasse;
            return responsable;
        }
    }
}
