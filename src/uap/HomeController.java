package uap;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button btnMenuHome;

    @FXML
    private Button btnPembHome;
    
    @FXML
    private Button btnUbahMenu;
    
    @FXML
    private Button btnTambahBarang;
    
    
    
    @FXML
    void goToMenuPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnMenuHome.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToPembPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pembelian.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnMenuHome.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    
    @FXML
    void gotoChangeMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addMakananPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnMenuHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        
//        MakananModel mkn = new MakananModel();
//        ArrayList<Makanan> listMakanan = mkn.getMakanan();
//        for (Makanan listMakanan1 : listMakanan) {
//            System.out.println("Nama: " + listMakanan1.getNama_produk());
//            System.out.println("Harga: " + listMakanan1.getHarga());
//            System.out.println(" ");
//        }
    }
    
    @FXML
    void gotoTambahBarang(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addBarangPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnTambahBarang.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    
}
