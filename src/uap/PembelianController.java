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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdbc.PenjualanModel;
import uap.Classes.Makanan;
import uap.Classes.Penjualan;

public class PembelianController implements Initializable{

    //buttons
    @FXML
    private Button addBtn;

    @FXML
    private Button btnCheckout;
    
    @FXML
    private Button pembBackBtn;

    //textFields
    @FXML
    private TextField discField;

    @FXML
    private TextField dtField;

    @FXML
    private TextField hargaField;

    @FXML
    private TextField jmlField;

    //combo box
    @FXML
    private ComboBox<String> dropdownMkn;
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showMakanan();
    }

    

    @FXML
    void addToCart(ActionEvent event) {
        PenjualanModel pjl = new PenjualanModel();
        
//        Penjualan pjl1 = new Penjualan();
    }
    
    @FXML
    void backHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) pembBackBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Struk.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnCheckout.getScene().getWindow();
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

    
}
