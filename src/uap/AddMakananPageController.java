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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.MakananModel;
import uap.Classes.Makanan;

public class AddMakananPageController implements Initializable {

    //Text Button
    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button rmvBtn;

    @FXML
    private Button btnEdit;
    
    //Text Field
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
    
    //Tabel Column
    @FXML
    private TableColumn<Makanan, Integer> colDayaTahan;

    @FXML
    private TableColumn<Makanan, Double> colDiskon;

    @FXML
    private TableColumn<Makanan, Double> colHarga;

    @FXML
    private TableColumn<Makanan, Integer> colId;

    @FXML
    private TableColumn<Makanan, Integer> colJumlah;

    @FXML
    private TableColumn<Makanan, String> colMakanan;
        
    //table stuff
    ObservableList<Makanan> menu;
    
    @FXML
    private TableView<Makanan> mknTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            showBarang();
        } catch (SQLException ex) {
            Logger.getLogger(MenuPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showBarang() throws SQLException{
        ObservableList<Makanan> list = getMakananList();
        colMakanan.setCellValueFactory(new PropertyValueFactory<>("nama_produk"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        menu = DBHelper.getMakananMenu();
        
        mknTable.setItems(list);
        
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
    Makanan mkn1 = new Makanan(namaField.getText(), hrgF, jmlF, discF, dtF);
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

    
    @FXML
    void editMakanan(ActionEvent even) throws IOException{
        MakananModel mkn = new MakananModel();
        double hrgF = Double.parseDouble(hargaField.getText());
        int jmlF = Integer.parseInt(jmlField.getText());
        double discF = Double.parseDouble(discField.getText());
        int dtF = Integer.parseInt(dtField.getText());
        Makanan mkn1 = new Makanan(namaField.getText(), hrgF, jmlF, discF, dtF);
        mkn.updMakananSQL(mkn1);
        if(mkn.status == true){
            lblStatus.setText("Berhasil Mengganti Data");
        }else{
            lblStatus.setText("Gagal Mengganti Data");
        }
        clearFieldValue();
    }
    
}
