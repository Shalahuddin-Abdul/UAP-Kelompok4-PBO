package uap;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.BarangModel;
import uap.Classes.Barang;

public class AddBarangPageController {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField discField;

    @FXML
    private TextField bcField;
    
    @FXML
    private TextField expField;

    @FXML
    private TextField hargaField;

    @FXML
    private TextField jmlField;

    @FXML
    private Text lblStatus;

    @FXML
    private TextField namaField;
    
    @FXML
    private Button rmvBtn;

    @FXML
    void addBarang(ActionEvent event) {
        BarangModel brg = new BarangModel();
        
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        
        Barang brg1 = new Barang(namaField.getText(), hrgF, jmlF, discF, bcField.getText(), expField.getText());
        brg.addBarangSQL(brg1);
        if(brg.status == true){
            lblStatus.setText("Berhasil Memasukkan Data");
        }else{
            lblStatus.setText("Gagal Memasukkan Data");
        }
        clearFieldValue();
    }

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    
    @FXML
    void rmvBarang(ActionEvent event) {
        BarangModel brg = new BarangModel();
        
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        
        Barang brg1 = new Barang(namaField.getText(), hrgF, jmlF, discF);
        brg.dltMakananSQL(brg1);
        if(brg.status == true){
            lblStatus.setText("Berhasil Memasukkan Data");
        }else{
            lblStatus.setText("Gagal Memasukkan Data");
        }
        clearFieldValue();
    }
    
    public void clearFieldValue(){
        namaField.clear();
        hargaField.clear();
        jmlField.clear();
        discField.clear();
        bcField.clear();
        expField.clear();
    }

}
