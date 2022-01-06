package com.hyperplanning.entities;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Utilisateur {
    public enum role{ENSEIGNANT,ETUDIANT,RESPONSABLE};
    protected int id;
    protected String nom;
    protected String prenoms;
    protected String email;
    protected String password;
    protected String role;

    public Utilisateur(){
    }

}
