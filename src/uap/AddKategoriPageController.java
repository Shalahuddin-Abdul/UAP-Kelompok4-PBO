package uap;

import db.DBHelper;
import static db.DBHelper.getConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jdbc.PenjualanModel;
import uap.Classes.Kategori;

public class AddKategoriPageController implements Initializable{

    @FXML
    private Button addKtg;

    @FXML
    private Button delKtg;
    
    @FXML
    private Button backBtn;


    @FXML
    private TextField ktgField;
    
    @FXML
    private ComboBox<String> dropdownKtg;

    @FXML
    void addKategori(ActionEvent event) {
        Kategori ktg = new Kategori(ktgField.getText());
        addKategori(ktg);
        ktgField.clear();
    }

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void deleteKategori(ActionEvent event) {
        String insert = "DELETE FROM ktg WHERE nama_kategori='" + dropdownKtg.getValue() + "';";
        try {
            if(CONN.createStatement().executeUpdate(insert)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
        }
        dropdownKtg.valueProperty().set(null);
    }
    
    Connection CONN = DBHelper.getConnection();
    public void addKategori(Kategori ktg){
        String insert = "INSERT INTO ktg VALUES (null, '" + ktg.getNama_kategori() + "');";
        try {
            if(CONN.createStatement().executeUpdate(insert)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Memasukkan Data");
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Memasukkan Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Memasukkan Data");
        }
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showKategori();
    }
    
    private void showKategori() {
        ObservableList<String> ktgList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * from ktg;";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                ktgList.add(rs.getString("nama_kategori"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        dropdownKtg.setItems(ktgList);
    }
}
