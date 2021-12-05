package com.hyperplanning.entities;
import lombok.Data;

@Data
public class Utilisateur {
    private int id;
    private String nom;
    private String prenoms;
    private String email;
    private String password;


    public static class UtilisateurBuilder{
        private int id;
        private String nom;
        private String prenoms;
        private String email;
        private String password;

        public UtilisateurBuilder setId(int id){
            this.id = id;
            return this;
        }

        public UtilisateurBuilder setNom(String nom){
            this.nom = nom;
            return this;
        }

        public UtilisateurBuilder setPrenoms(String prenoms){
            this.prenoms = prenoms;
            return this;
        }

        public UtilisateurBuilder setEmail(String email){
            this.email = email;
            return this;
        }

        public UtilisateurBuilder setPassword(String password){
            this.password = password;
            return this;
        }

        public Utilisateur build(){
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.id = id;
            utilisateur.nom = nom;
            utilisateur.prenoms = prenoms;
            utilisateur.email = email;
            utilisateur.password = password;
            return utilisateur;
        }

    }
}
