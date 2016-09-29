/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Problema;

/**
 *
 * @author jc
 */
public class ItemComboProblemas {
    
    private String descricao;
    private int codigo;
    
    public ItemComboProblemas(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    @Override
    public String toString() {
        return descricao;
    }

    public static ObservableList<ItemComboProblemas> buscarItems() {

        ObservableList<ItemComboProblemas> retornar = FXCollections.observableArrayList();
        List<Problema> equipamentos = ProblemaControle.obterListaProblemas();

        for (Problema it : equipamentos) {
            retornar.add(new ItemComboProblemas(it.getDescricao(), it.getCodigo()));
        }
        
        return retornar;
    }
}
