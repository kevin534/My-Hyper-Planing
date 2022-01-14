package com.hyperplanning.entities;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;


@SuperBuilder
@Data
public class Enseignant extends Utilisateur{
    private List<Matiere> matiere ;

    @Override
    public String toString(){
        return super.toString();
    }
}
