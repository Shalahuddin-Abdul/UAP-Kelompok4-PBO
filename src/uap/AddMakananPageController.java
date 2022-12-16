package uap;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.MakananModel;
import uap.Classes.Makanan;

public class AddMakananPageController {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField discField;

    @FXML
    private TextField dtField;

    @FXML
    private TextField hargaField;

    @FXML
    private TextField jmlField;

    @FXML
    private TextField namaField;
    
    @FXML
    private Text lblStatus;
    
    @FXML
    private Button rmvBtn;

    @FXML
    void addMakanan(ActionEvent event) {
        MakananModel mkn = new MakananModel();
//        ArrayList<Makanan> listMakanan = mkn.getMakanan();
//        for (Makanan lm : listMakanan) {
//            System.out.println("Nama: " + lm.getNama_produk());
//            System.out.println("Harga: " + lm.getHarga());
//            System.out.println(" ");
//        }
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        int dtF = Integer.parseInt(dtField.getText());
        Makanan mkn1 = new Makanan(namaField.getText(), hrgF, jmlF, discF, dtF);
        mkn.addMakananSQL(mkn1);
        if(mkn.status == true){
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
    void rmvMakanan(ActionEvent event) throws IOException{
        MakananModel mkn = new MakananModel();
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        int dtF = Integer.parseInt(dtField.getText());
        Makanan mkn1 = new Makanan(namaField.getText(), hrgF, jmlF, discF);
        mkn.dltMakananSQL(mkn1);
        if(mkn.status == true){
            lblStatus.setText("Berhasil Menghapus Data");
        }else{
            lblStatus.setText("Gagal Menghapus Data");
        }
        clearFieldValue();
    }
    
    public void clearFieldValue(){
        namaField.clear();
        hargaField.clear();
        jmlField.clear();
        discField.clear();
        dtField.clear();
    }
}
