package com.hyperplanning.entities;
import lombok.Data;

@Data
public class Salle {
    private int codeSalle;
    private String libelleSalle;
    private String batiment;

    public static class SalleBuilder{
        private int codeSalle;
        private String libelleSalle;
        private String batiment;

        public SalleBuilder setCodeSalle(int codeSalle){
            this.codeSalle = codeSalle;
            return this;
        }

        public SalleBuilder setlibelleSalle(String libelleSalle){
            this.libelleSalle = libelleSalle;
            return this;
        }

        public SalleBuilder setBatiment(String batiment){
            this.batiment = batiment;
            return this;
        }

        public Salle build(){
            Salle salle = new Salle();
            salle.codeSalle = codeSalle;
            salle.libelleSalle = libelleSalle;
            salle.batiment = batiment;
            return salle;
        }
    }
}
