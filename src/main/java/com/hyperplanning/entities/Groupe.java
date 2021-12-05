package com.hyperplanning.entities;
import lombok.Data;

@Data
public class Groupe {
    private int groupeClasse;

    public static class GroupeBuilder {

        private int groupeClasse;
        public GroupeBuilder setGroupeClasse(int groupeClasse){
            this.groupeClasse = groupeClasse;
            return this;
        }

        public Groupe build(){
            Groupe groupe = new Groupe();
            groupe.groupeClasse = groupeClasse;
            return groupe;
        }
    }

}
