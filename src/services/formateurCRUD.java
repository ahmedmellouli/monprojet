/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.formateur;
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
public class formateurCRUD {
    Connection cnx2 ;
    public formateurCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterFormateur(formateur f){
        
        try {
            String requete = "INSERT INTO formateur (horaire,specialite,num_membres)"
                    + " VALUES (?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setDate(1, f.getHoraire());
            pst.setString(2, f.getSpecialite());
            pst.setInt(3, f.getNum_membres());
            pst.executeUpdate();
            System.out.println("Votre formateur est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void supprimerFormateur() {

        try {
            System.out.println("Entrez l'Id de formateur à supprimer");
            Scanner sc = new Scanner(System.in);
            String a = sc.nextLine();

            String sql = "delete from formateur where Id_formateur=" + a;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("formateur est supprimée aves succeé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void supprimer(formateur f){
    try {
            String sql = "DELETE FROM `formateur` WHERE `Id_formateur` = "+ f.getId_formateur()+ "";

         
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();
            
            System.out.println("formateur supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(formateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifierFormateur(formateur f, int id) {
        try {
            String sql = "UPDATE formateur SET horaire=?, specialite=?, num_membres=? WHERE Id_formateur="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
         
            ste.setDate(1, f.getHoraire());
            ste.setString(2, f.getSpecialite());
            ste.setInt(3, f.getNum_membres());   
            
            
            
            ste.executeUpdate();
            System.out.println("Votre formateur est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     }
    public void modifier(formateur f){
        String req = "UPDATE formateur set horaire=?, specialite=?, num_membres=?  WHERE Id_formateur="+ f.getId_formateur()+ "";
    try {
            PreparedStatement ste = cnx2.prepareStatement(req);
           
            ste.setDate(1, f.getHoraire());
            ste.setString(2, f.getSpecialite());
            ste.setInt(3, f.getNum_membres());   
            ste.setInt(4, f.getId_formateur());
        
           ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de formateur a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(formateurCRUD.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
    
    public List<formateur> afficherFormateur(){
        List<formateur> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM formateur";
            Statement st = cnx2.createStatement();
            ResultSet rs=st.executeQuery(requete3);
            while(rs.next()){
                formateur f = new formateur();
                f.setId_formateur(rs.getInt(1));
                f.setHoraire(rs.getDate("horaire"));
                f.setSpecialite(rs.getString("Specialite"));
                f.setNum_membres(rs.getInt("Num_membres"));
                myList.add(f);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    public List<formateur> rechercherFormateur(int id){
        List<formateur> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM formateur WHERE Id_formateur="+id;
            Statement st = cnx2.createStatement();
            ResultSet rs=st.executeQuery(requete3);
            while(rs.next()){
                formateur f = new formateur();
                f.setId_formateur(rs.getInt(1));
                f.setHoraire(rs.getDate("horaire"));
                f.setSpecialite(rs.getString("Specialite"));
                f.setNum_membres(rs.getInt("Num_membres"));
                myList.add(f);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
}

