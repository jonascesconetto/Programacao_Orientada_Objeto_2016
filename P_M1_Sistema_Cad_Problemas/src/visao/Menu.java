/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jc
 */
public class Menu implements Initializable{
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    //action faz relação ao evento do botão precionado
    public void cadastrarNovoProblema(ActionEvent e) throws IOException{ 
        Button bt = (Button) e.getSource();
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
                
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("CadastrarNovoProblema.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("MENU para Cad Problema");
    }
    
    public void visualizarProblemas(ActionEvent e) throws IOException{
        Button bt = (Button) e.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
                
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("VisualizarProblemas.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("MENU para Visualizar Problemas");
    }
    
    public void resolverProblemas(ActionEvent e) throws IOException{
        Button bt = (Button) e.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
                
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("ResolverProblemas.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("MENU para Resolver Problema");
    }
    
}
