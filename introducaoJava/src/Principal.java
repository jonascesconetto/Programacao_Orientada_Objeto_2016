
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
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
 * @author jonas
 */
/*
public class Principal {
    public static void main(String[] args) {
        Menu.exibirMenu();
    }
}
*/
public class Principal extends Application{
    
    public static void main(String[] args) {
        launch(); 
    }
    
     @Override
     public void start(Stage palco) throws Exception {
         
         palco.setTitle("Equipamento/Manutenção");
         //Abre o FXML
         //Pane principal = FXMLLoader.load(getClass().getResource("visao/EquipamentoVisao.fxml"));
         //Pane principal = FXMLLoader.load(getClass().getResource("visao/ManutencaoVisao.fxml"));
         Pane principal = FXMLLoader.load(getClass().getResource("visao/Menu.fxml"));
         Scene cena = new Scene(principal);
         
         palco.setScene(cena);
         palco.setWidth(400);
         palco.setHeight(400);
         palco.show();
     }
}  


