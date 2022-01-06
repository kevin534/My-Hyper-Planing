package com.hyperplanning.entities;
import javafx.scene.control.CheckBox;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Etudiant extends Utilisateur{
    private Groupe groupeClasse;
    private CheckBox present = new CheckBox();



       /* public Etudiant(int id, String nom, String prenoms, String email,String password, Boolean present) {
        super.builder().id(id).nom(nom).prenoms(prenoms).email(email).password(password);
        this.present.setSelected(present);

    }*/
    public Etudiant(int id, String nom, String prenoms, String email,String password, Boolean present) {
        //super(builder().id(id).nom(nom).prenoms(prenoms).email(email).password(pa));
        this.nom = nom;
        this.id = id;
        this.prenoms = prenoms;
        this.email = email;
        this.password = password;


        this.present.setSelected(present);
    }
    public CheckBox getPresent() {
        return present;
    }

    public Groupe getGroupeClasse() {
        return groupeClasse;
    }

    public void setGroupeClasse(Groupe groupeClasse) {
        this.groupeClasse = groupeClasse;
    }
    public void setPresent(Boolean present) {
        this.present.setSelected(present);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public void setPresent(CheckBox present) {
        this.present = present;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
