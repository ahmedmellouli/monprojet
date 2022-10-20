/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class formateur {
    private int Id_formateur;
    private Date horaire;
    private String specialite;
    private int num_membres;

    public formateur() {
    }

    public formateur(Date horaire, String specialite, int num_membres) {
        this.horaire = horaire;
        this.specialite = specialite;
        this.num_membres = num_membres;
    }

    public formateur(int Id_formateur, Date horaire, String specialite, int num_membres) {
        this.Id_formateur = Id_formateur;
        this.horaire = horaire;
        this.specialite = specialite;
        this.num_membres = num_membres;
    }

    public int getId_formateur() {
        return Id_formateur;
    }

    public void setId_formateur(int Id_formateur) {
        this.Id_formateur = Id_formateur;
    }

    public Date getHoraire() {
        return horaire;
    }

    public void setHoraire(Date horaire) {
        this.horaire = horaire;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNum_membres() {
        return num_membres;
    }

    public void setNum_membres(int num_membres) {
        this.num_membres = num_membres;
    }

    @Override
    public String toString() {
        return "formateur{" + "Id_formateur=" + Id_formateur + ", horaire=" + horaire + ", specialite=" + specialite + ", num_membres=" + num_membres + '}';
    }
    
}
