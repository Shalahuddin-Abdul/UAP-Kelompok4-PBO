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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uap.Classes.Barang;
import uap.Classes.Makanan;

public class ListBarangPageController implements Initializable{

//    @FXML
//    private Button backBtn;
//
//    @FXML
//    private Button buyBtn;

    @FXML
    private TableColumn<Barang, String> colBrg;

    @FXML
    private TableColumn<Barang, Double> colDsc;

    @FXML
    private TableColumn<Barang, Integer> colExp;

    @FXML
    private TableColumn<Barang, Double> colHrg;

    @FXML
    private TableColumn<Barang, Integer> colJml;

    @FXML
    private TableColumn<Barang, Integer> colBc;

    @FXML
    private TableView<Barang> tableBarang;
    
    ObservableList<Makanan> barang;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            showBarang();
        } catch (SQLException ex) {
            Logger.getLogger(MenuPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Barang> getBarangList() throws SQLException{
        ObservableList<Barang> barangList = FXCollections.observableArrayList();
        Connection conn = getConnection();
//        String query = "SELECT brg.nama_produk, concat('Rp. ', FORMAT (brg.harga, 'c', 'id-ID')) as `Harga`, brg.jumlah, concat('Rp. ', FORMAT (brg.diskon, 'c', 'id-ID')) as `Diskon`, brg.barcode, brg.expired from brg;";
        String query = "SELECT * FROM brg;";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Barang brg;
            while(rs.next()){
                brg = new Barang(rs.getString("nama_produk"), rs.getDouble("Harga"), rs.getInt("jumlah"), rs.getDouble("Diskon"), rs.getString("barcode"), rs.getString("expired"));
                barangList.add(brg);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return barangList;
    }
    
    public void showBarang() throws SQLException{
        ObservableList<Barang> list = getBarangList();
        colBrg.setCellValueFactory(new PropertyValueFactory<>("nama_produk"));
        colHrg.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colJml.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colDsc.setCellValueFactory(new PropertyValueFactory<>("diskon"));
        colBc.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("expired"));
        
        tableBarang.setItems(list);
        
    }

//    @FXML
//    void backHome(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
//        Parent root = loader.load();
//        
//        Stage stage = (Stage) backBtn.getScene().getWindow();
//        stage.setScene(new Scene(root));
//    }
//
//    @FXML
//    void gotoPembelian(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pembelian.fxml"));
//        Parent root = loader.load();
//        
//        Stage stage = (Stage) buyBtn.getScene().getWindow();
//        stage.setScene(new Scene(root));
//    }

}
