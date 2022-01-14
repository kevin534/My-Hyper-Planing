package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;

@Builder
@Data
public class Reservation {
    private int id;
    private String materiel;
    private Date dateDebut;
    private Date dateFin;
    private Enseignant enseignant;



}
