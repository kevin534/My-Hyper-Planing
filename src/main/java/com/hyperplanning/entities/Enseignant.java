package com.hyperplanning.entities;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@SuperBuilder
@Data
public class Enseignant extends Utilisateur{
    private List<Matiere> matiere = new ArrayList<Matiere>();


}
