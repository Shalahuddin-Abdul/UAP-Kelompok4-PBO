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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uap.Classes.Barang;
import jdbc.BarangModel;
import uap.Classes.Kategori;
import uap.Classes.Makanan;

public class AddBarangPageController implements Initializable{
    
    //buttons
    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button rmvBtn;

    //text field
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

    //texts
    @FXML
    private Text lblStatus;

    @FXML
    private TextField namaField;
    
    //table column
    @FXML
    private TableColumn<Barang, String> colBarang;

    @FXML
    private TableColumn<Barang, String> colBarcode;

    @FXML
    private TableColumn<Barang, Double> colDiskon;

    @FXML
    private TableColumn<Barang, Integer> colExpired;

    @FXML
    private TableColumn<Barang, Double> colHarga;

    @FXML
    private TableColumn<Barang, Integer> colJumlah;

    @FXML
    private TableColumn<Kategori, String> colKategori;
    
    ObservableList<Makanan> barang;
    
    @FXML
    private TableView<Barang> tableBarang;
    
    @FXML
    private ComboBox<String> dropdownKategori;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            showBarang();
            showKategori();
        } catch (SQLException ex) {
            Logger.getLogger(MenuPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        dropdownKategori.setItems(ktgList);
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
                brg = new Barang(rs.getString("nama_produk"), rs.getDouble("Harga"), rs.getInt("jumlah"), rs.getDouble("Diskon"), rs.getString("barcode"), rs.getString("expired"), rs.getString("nama_kategori"));
                barangList.add(brg);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return barangList;
    }
    
    public void showBarang() throws SQLException{
        ObservableList<Barang> list = getBarangList();
        colBarang.setCellValueFactory(new PropertyValueFactory<>("nama_produk"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colDiskon.setCellValueFactory(new PropertyValueFactory<>("diskon"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colExpired.setCellValueFactory(new PropertyValueFactory<>("expired"));
        colKategori.setCellValueFactory(new PropertyValueFactory<>("nama_kategori"));
//        barang = DBHelper.getMakananMenu();
        
        tableBarang.setItems(list);
        
    }
    
    @FXML
    void addBarang(ActionEvent event) {
        BarangModel brg = new BarangModel();
        
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        
        Barang brg1 = new Barang(namaField.getText(), hrgF, jmlF, discF, bcField.getText(), expField.getText(), dropdownKategori.getValue());
        brg.addBarangSQL(brg1);
        if(brg.status == true){
            lblStatus.setText("Berhasil Memasukkan Data");
        }else{
            lblStatus.setText("Gagal Memasukkan Data");
        }
        System.out.println(dropdownKategori.getValue());
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
        dropdownKategori.valueProperty().set(null);
    }
}
