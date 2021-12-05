package com.hyperplanning.entities;
import lombok.Data;

@Data
public class Matiere {
    private int codeMatiere;
    private String libelleMatiere;

    public static class MatiereBuilder{
        private int codeMatiere;
        private String libelleMatiere;

        public MatiereBuilder setCodeMatiere(int codeMatiere){
            this.codeMatiere = codeMatiere;
            return this;
        }

        public MatiereBuilder setLibelleMatiere(String libelleMatiere){
            this.libelleMatiere = libelleMatiere;
            return this;
        }

        public Matiere build(){
            Matiere matiere = new Matiere();
            matiere.codeMatiere = codeMatiere;
            matiere.libelleMatiere = libelleMatiere;
            return matiere;
        }
    }
}
