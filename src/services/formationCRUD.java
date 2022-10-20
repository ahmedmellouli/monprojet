/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.candidat;
import entities.formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class formationCRUD {
    Connection cnx2 ;
    public formationCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterFormation(formation f){
        
        try {
            String requete = "INSERT INTO formation (Id_Formateur,Date_For,Nom_For,Numbr_Max_Per)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, f.getId_Formateur());
            pst.setDate(2, f.getDate_For());
            pst.setString(3, f.getNom_For());
            pst.setInt(4, f.getNumbr_Max_Per());
            
            pst.executeUpdate();
            System.out.println("Votre formation est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public void supprimer(formation f) {
try {
            String sql = "DELETE FROM `formation` WHERE `Id_For` = "+ f.getId_For()+ "";

         
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();
            
            System.out.println("formation supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(formationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void supprimerFormation() {

        try {
            System.out.println("Entrez l'Id de formation à supprimer");
            Scanner sc = new Scanner(System.in);
            String a = sc.nextLine();

            String sql = "delete from formation where Id_For=" + a;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("formation est supprimée aves succeé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
    public void modifier(formation f) {
        String req = "UPDATE formation set Id_Formateur=?, Date_For=?, Nom_For=? ,Numbr_Max_Per=? WHERE Id_For="+ f.getId_For()+ "";
    try {
            PreparedStatement ste = cnx2.prepareStatement(req);
           
            ste.setInt(1, f.getId_Formateur());
            ste.setDate(2, f.getDate_For());
            ste.setString(3, f.getNom_For());   
            ste.setInt(4, f.getNumbr_Max_Per());
        
           ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de formation a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(formateurCRUD.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
     public void modifierFormation(formation f, int id) {
        try {
            String sql = "UPDATE formation SET Id_Formateur=?, Date_For=?, Nom_For=? ,Numbr_Max_Per=? WHERE Id_For="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
         
            ste.setInt(1, f.getId_Formateur());
            ste.setDate(2, f.getDate_For());
            ste.setString(3, f.getNom_For());   
            ste.setInt(4, f.getNumbr_Max_Per());
            ste.executeUpdate();
            System.out.println("Votre formation est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
        public List<formation> afficherForamtion(){
        List<formation> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM formation";
            Statement st = cnx2.createStatement();
            ResultSet rs=st.executeQuery(requete3);
            while(rs.next()){
                formation f = new formation();
                f.setId_For(rs.getInt(1));
                f.setId_Formateur(rs.getInt("Id_Formateur"));
                f.setDate_For(rs.getDate("Date_For"));
                f.setNom_For(rs.getString("Nom_For"));
                f.setNumbr_Max_Per(rs.getInt("Numbr_Max_Per"));
                myList.add(f);
                
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
     public List<formation> rechercherForamtion(int id){
        List<formation> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM formation WHERE Id_For="+id;
            Statement st = cnx2.createStatement();
            ResultSet rs=st.executeQuery(requete3);
            while(rs.next()){
                formation f = new formation();
                f.setId_For(rs.getInt(1));
                f.setId_Formateur(rs.getInt("Id_Formateur"));
                f.setDate_For(rs.getDate("Date_For"));
                f.setNom_For(rs.getString("Nom_For"));
                f.setNumbr_Max_Per(rs.getInt("Numbr_Max_Per"));
                myList.add(f);
                
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
