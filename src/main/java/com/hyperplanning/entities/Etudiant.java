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
    private String excuse;


       public Etudiant(int id, String nom, String prenoms, String email,String password, Boolean present,String excuse) {
            this.nom = nom;
           this.id = id;
           this.prenoms = prenoms;
           this.email = email;
           this.password = password;
           this.present.setSelected(present);
           this.excuse = excuse;
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



    @Override
    public String toString(){
        return super.toString();
    }
}
