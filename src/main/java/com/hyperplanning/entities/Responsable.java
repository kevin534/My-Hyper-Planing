package com.hyperplanning.entities;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Responsable extends Utilisateur{
    private Groupe groupeClasse;

    @Override
    public String toString(){
        return super.toString();
    }
}
