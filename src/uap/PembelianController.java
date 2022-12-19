package uap;

import db.DBHelper;
import static db.DBHelper.getConnection;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jdbc.MakananModel;
import jdbc.PenjualanModel;
import uap.Classes.Barang;
import uap.Classes.Makanan;
import uap.Classes.Penjualan;
import uap.Classes.Produk;

public class PembelianController implements Initializable{
    
    //buttons
    @FXML
    private Button addBtnBarang;

    @FXML
    private Button addBtnMkn;

    @FXML
    private Button btnCheckout;
    
    @FXML
    private Button pembBackBtn;
  
    //field
    @FXML
    private TextField jmlFieldBrg;

    @FXML
    private TextField jmlFieldMkn;


    //combo box
    @FXML
    private ComboBox<String> dropdownMkn;
    
    @FXML
    private ComboBox<String> dropdownBrg;
    
    @FXML
    private TextField idField;
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showMakanan();
       showBarang();
    }
    
    @FXML
    void dltPembelian(ActionEvent event) {
        Connection CONN = DBHelper.getConnection();
        String delete = "DELETE FROM pjl WHERE id=" + idField.getText() + ";";
        try {
            if(CONN.createStatement().executeUpdate(delete)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
        }
    }

    ArrayList<Produk> listProduk = new ArrayList<>();
    Penjualan pjl1 = new Penjualan(listProduk);
    Double totalPembelian = 0.0;

    @FXML
    void addToCartMkn(ActionEvent event) throws SQLException {
//        ObservableList<Double> mknHargaList = FXCollections.observableArrayList();
        Double hargaMakanan = 0.0;
        Connection conn = getConnection();
        String query = "SELECT * from mkn where nama_produk = '" + dropdownMkn.getValue() +"';";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                hargaMakanan = rs.getDouble("harga");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        int jmlMkn = Integer.parseInt(jmlFieldMkn.getText());    
        Makanan mkn = new Makanan(dropdownMkn.getValue(), jmlMkn*hargaMakanan, jmlMkn, 0);
        listProduk.add(mkn);
        dropdownMkn.valueProperty().set(null);
    }
    
    @FXML
    void addToCartBrg(ActionEvent event) throws SQLException {
        Double hargaBarang = 0.0;
        Connection conn = getConnection();
        String query = "SELECT * from brg where nama_produk = '" + dropdownBrg.getValue() +"';";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                hargaBarang = rs.getDouble("harga");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        int jmlBrg = Integer.parseInt(jmlFieldBrg.getText());
        Barang brg = new Barang(dropdownBrg.getValue(), jmlBrg*hargaBarang, jmlBrg, 0 );
        listProduk.add(brg);  
        dropdownBrg.valueProperty().set(null);
    }
    
    @FXML
    void checkout(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Struk.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) btnCheckout.getScene().getWindow();
//        stage.setScene(new Scene(root));
        PenjualanModel pjl = new PenjualanModel();
        for(int i = 0; i<listProduk.size(); i++){
            totalPembelian += listProduk.get(i).getHarga();
        }

        System.out.println(totalPembelian);
        
        if(listProduk.size()>0){
            pjl.addPenjualan(pjl1);
        }
        String output = "";
        for(int i = 0; i<listProduk.size(); i++){
            String everything = listProduk.get(i).getNama_produk();
            Double everything2 = listProduk.get(i).getHarga();
            int everything3 = listProduk.get(i).getJumlah();

            output += everything +" "+ everything2 + " " + everything3 + "pcs\n";       
        }
        output += "Total Pembelian: " + totalPembelian;
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 18));
        JOptionPane.showMessageDialog(null, output);
        
//        Parent parent = FXMLLoader.load(getClass().getResource("Struk.fxml"));
//        Scene scene = new Scene(parent);
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UTILITY);
//        stage.setScene(scene);
//        stage.show();
    }
        
    @FXML
    void backHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) pembBackBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    



    private void showMakanan() {
        ObservableList<String> mknList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * from mkn;";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                mknList.add(rs.getString("nama_produk"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        dropdownMkn.setItems(mknList);
    }
    
    private void showBarang() {
        ObservableList<String> brgList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * from brg;";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                brgList.add(rs.getString("nama_produk"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        dropdownBrg.setItems(brgList);
    }

    

    
    
}
