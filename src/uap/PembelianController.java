package uap;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PembelianController {

    @FXML
    private Button addBtn;
    
    @FXML
    private Button btnCheckout;

    @FXML
    private Button pembBackBtn;
    
    @FXML
    private TextField discField;

    @FXML
    private TextField dtField;

    @FXML
    private TextField hargaField;

    @FXML
    private TextField jmlField;


    @FXML
    void addToCart(ActionEvent event) {
        
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

}
