package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Salle {
    private int codeSalle;
    private String libelleSalle;
    private String batiment;


}
