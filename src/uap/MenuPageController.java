package uap;

import db.DBHelper;
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
import uap.Classes.Makanan;

public class MenuPageController implements Initializable{

    @FXML
    private TableColumn<Makanan, Double> colHarga;

    @FXML
    private TableColumn<Makanan, String> colMakanan;

//    @FXML
//    private Button menuBackBtn;

    @FXML
    private TableView<Makanan> menuTable;
    
    ObservableList<Makanan> menu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            showMakanan();
        } catch (SQLException ex) {
            Logger.getLogger(MenuPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Makanan> getMakananList() throws SQLException{
        ObservableList<Makanan> makananList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * from mkn;";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Makanan mkn;
            while(rs.next()){
                mkn = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getInt("daya_tahan"));
                makananList.add(mkn);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return makananList;
    }
    
    
    public void showMakanan() throws SQLException{
        ObservableList<Makanan> list = getMakananList();
        colMakanan.setCellValueFactory(new PropertyValueFactory<>("nama_produk"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
        menuTable.setItems(list);
        
    }
    
//    @FXML
//    void backHome(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) menuBackBtn.getScene().getWindow();
//        stage.setScene(new Scene(root));
//    }
    
    
}
