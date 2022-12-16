package uap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import uap.Classes.Makanan;

public class MenuPageController {

    @FXML
    private TableColumn<Makanan, Double> colHarga;

    @FXML
    private TableColumn<Makanan, String> colMakanan;

    @FXML
    private Button menuBackBtn;

    @FXML
    private TableView<Makanan> menuTable;
    
    ObservableList<Makanan> menu;
    
    int index = -1;
    
    DBHelper conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void backHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) menuBackBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
