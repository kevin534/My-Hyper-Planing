package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Groupe {
    private int groupeClasse;
    private String libelleClasse;


    @Override
    public String toString(){
            return this.libelleClasse;
    }




}
