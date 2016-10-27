package visao;




import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import visao.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jc
 */

public class Principal extends Application{
    
    public static void main(String[] args) {
        launch(); 
    }
    
     @Override
     public void start(Stage palco) throws Exception {
         
         palco.setTitle("TELA PRINCIPAL");
         Pane principal = FXMLLoader.load(getClass().getResource("Menu.fxml"));
         Scene cena = new Scene(principal);
         
         palco.setScene(cena);
         palco.setWidth(600);
         palco.setHeight(400);
         palco.show();
     }
} 