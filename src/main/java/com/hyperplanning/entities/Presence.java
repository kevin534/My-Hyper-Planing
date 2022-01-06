package com.hyperplanning.entities;

import javafx.scene.control.CheckBox;

import lombok.experimental.SuperBuilder;

@SuperBuilder

public class Presence {
    private Etudiant etudiant;
    private Cours cours;

    private CheckBox present = new CheckBox();


    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public CheckBox getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present.setSelected(present);
    }
}
