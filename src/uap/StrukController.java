package uap;

import static db.DBHelper.getConnection;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import uap.Classes.Barang;
import uap.Classes.Makanan;
import uap.Classes.Penjualan;
import uap.Classes.Produk;

public class StrukController implements Initializable{

    @FXML
    private TableColumn<Penjualan, Double> colHarga;

    @FXML
    private TableColumn<Penjualan, Integer> colJumlah;

    @FXML
    private TableColumn<Penjualan, String> colProduk;

    @FXML
    private Text lblHarga;

    @FXML
    private TableView<Produk> strukTable;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showPenjualan();
            lblHarga.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(StrukController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Produk> getPenjualanList() throws SQLException{
        ObservableList<Produk> produkList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM pjl;";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Produk pdk;
            while(rs.next()){
                pdk = new Produk(rs.getString("listProduk"), rs.getDouble("total"), rs.getInt("jumlahProduk"));
                produkList.add(pdk);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
            
        
        return produkList;
    }
    
    
    public void showPenjualan() throws SQLException{
        ObservableList<Produk> list = getPenjualanList();

        colProduk.setCellValueFactory(new PropertyValueFactory<>("listProduk"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("total"));
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahProduk"));
        
        strukTable.setItems(list);
        
    }
    
    
    

}
