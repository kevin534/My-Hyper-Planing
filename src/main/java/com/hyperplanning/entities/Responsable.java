package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Responsable extends Utilisateur{
    private Groupe groupeClasse;


}
