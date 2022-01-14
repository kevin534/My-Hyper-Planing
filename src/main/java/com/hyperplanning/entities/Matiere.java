package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Matiere {
    private int codeMatiere;
    private String libelleMatiere;

    @Override
    public String toString(){
        return this.libelleMatiere;
    }


}
