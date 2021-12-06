package com.hyperplanning.entities;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Utilisateur {
    private int id;
    private String nom;
    private String prenoms;
    private String email;
    private String password;


}
