/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import entities.formateur;
import entities.formation;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.formateurCRUD;
import services.formationCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormationController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfIDF;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNum;
    @FXML
    private Button btnAjouter;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btnSup;
    @FXML
    private TableView<formation> Table_Formations;
    @FXML
    private TableColumn<formation, Integer> a_ID;
    @FXML
    private TableColumn<formation, Integer> a_IDF;
    @FXML
    private TableColumn<formation, String> a_Nom;
    @FXML
    private TableColumn<formation, DatePicker> a_Date;
    @FXML
    private TableColumn<formation, Integer> a_NC;
    @FXML
    private Button btnModif;
    @FXML
    private TextField tfS;
    @FXML
    private TextField tfM;
    @FXML
    private DatePicker dpH;
    @FXML
    private TableView<formateur> Table_Formateur;
    @FXML
    private TableColumn<formateur, Integer> C1;
    @FXML
    private TableColumn<formateur, String> C2;
    @FXML
    private TableColumn<formateur, Date> C3;
    @FXML
    private TableColumn<formateur, Integer> C4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    loadTableFormation();//formation
   // loadTableFormation();// formation affiche all 
   loadTableFormateur();
    
    }  
      ObservableList<formation> oblist = FXCollections.observableArrayList();
      ObservableList<formateur> oblist1 = FXCollections.observableArrayList();
     formationCRUD fcd= new formationCRUD();
     formateurCRUD ffcd = new formateurCRUD();
   
     private void loadTableFormation(){//affiche foramtion
        List <formation> fs =fcd.afficherForamtion();
        fs.forEach(e->oblist.add(e));
        System.out.println(oblist);
        a_ID.setCellValueFactory(new PropertyValueFactory<>("Id_For"));
        a_IDF.setCellValueFactory(new PropertyValueFactory<>("Id_Formateur"));
        a_Nom.setCellValueFactory(new PropertyValueFactory<>("Nom_For"));
        a_Date.setCellValueFactory(new PropertyValueFactory<>("Date_For"));
        a_NC.setCellValueFactory(new PropertyValueFactory<>("Numbr_Max_Per"));
     Table_Formations.setItems(oblist);
    }

    
     

    
    

    @FXML
    private void formation_select(MouseEvent event) {
       
        int index = Table_Formations.getSelectionModel().getSelectedIndex();
        formation f = Table_Formations.getSelectionModel().getSelectedItem();
        tfID.setText(a_ID.getCellData(index).toString());
        tfIDF.setText(a_IDF.getCellData(index).toString());
        tfNum.setText(a_NC.getCellData(index).toString());
        tfNom.setText(a_Nom.getCellData(index));
    }

    @FXML
    private void addFormateur(ActionEvent event) {
        java.sql.Date date =Date.valueOf(dpH.getValue());
        int M;
        M = Integer.parseInt(tfM.getText());
        
        
  if((tfS.getText().equals("")) ||
                (tfM.getText().equals(""))||
                
                (date.equals(""))
                ){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir tous les champs ");
        }
        else {
     formateur f= new formateur(date,tfS.getText(),M); 
     formationCRUD fcd= new formationCRUD();
        ffcd.ajouterFormateur(f);
        JOptionPane.showMessageDialog(null, "Formateur Créée");
    }
    }
    private void loadTableFormateur(){//affiche foramtion
        List <formateur> fs =ffcd.afficherFormateur();
        fs.forEach(e->oblist1.add(e));
        System.out.println(oblist1);
        C1.setCellValueFactory(new PropertyValueFactory<>("Id_formateur"));
        C2.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        C3.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        C4.setCellValueFactory(new PropertyValueFactory<>("num_membres"));
        
     Table_Formateur.setItems(oblist1);
    }

    @FXML
    private void deleteFormateur(MouseEvent event) {
        formateur f=  Table_Formateur.getSelectionModel().getSelectedItem();
        ffcd.supprimer(f);
        Table_Formateur.getItems().clear();
        loadTableFormateur();
    }

    @FXML
    private void select_formateur(MouseEvent event) {
        int index = Table_Formateur.getSelectionModel().getSelectedIndex();
        formateur f = Table_Formateur.getSelectionModel().getSelectedItem();
        tfS.setText(C2.getCellData(index).toString());
        
        tfM.setText(C4.getCellData(index).toString());
        
    }

    @FXML
    private void updateFormateur(MouseEvent event) {
        formateur f =  Table_Formateur.getSelectionModel().getSelectedItem();
        
        int nbr1=Integer.parseInt(tfM.getText());
            f.setSpecialite(tfS.getText());
            f.setNum_membres(nbr1);
        ffcd.modifier(f);
        Table_Formateur.getItems().clear();
        loadTableFormateur();
        
    }

    @FXML
    private void addFormation(ActionEvent event) {
        java.sql.Date date =Date.valueOf(dpDate.getValue());
        int id;
        id = Integer.parseInt(tfID.getText());
         int idF;
        idF = Integer.parseInt(tfIDF.getText());
         int tfNu;
        tfNu = Integer.parseInt(tfNum.getText());
        
  if((tfID.getText().equals("")) ||
                (tfIDF.getText().equals(""))||
                (tfNom.getText().equals(""))||
                (tfNum.getText().equals(""))||
                (date.equals(""))
                ){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir tous les champs ");
        }
        else {
     formation f= new formation(idF, idF, date, tfNom.getText(), tfNu); 
     formationCRUD fcd= new formationCRUD();
        fcd.ajouterFormation(f);
        JOptionPane.showMessageDialog(null, "Formation Créée");
    }
    }

    @FXML
    private void deleteFormation(MouseEvent event) {
        formation f=  Table_Formations.getSelectionModel().getSelectedItem();
        fcd.supprimer(f);
        Table_Formations.getItems().clear();
        loadTableFormation();
    }

    @FXML
    private void updateFormation(MouseEvent event) {
        formation f =  Table_Formations.getSelectionModel().getSelectedItem();
        int nbr1=Integer.parseInt(tfID.getText());
            int nbr2=Integer.parseInt(tfIDF.getText());  
            int nbr3=Integer.parseInt(tfNum.getText());  
            f.setId_For(nbr1);
            f.setId_Formateur(nbr2);
            f.setNumbr_Max_Per(nbr3);
            
        f.setNom_For(tfNom.getText());
        fcd.modifier(f);
        Table_Formations.getItems().clear();
        loadTableFormation();
    }
    
}
    

