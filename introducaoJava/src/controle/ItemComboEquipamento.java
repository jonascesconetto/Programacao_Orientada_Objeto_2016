/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Equipamento;

/**
 *
 * @author jc
 */
public class ItemComboEquipamento {

    private String descricao;
    private String patrimonio;

    public ItemComboEquipamento(String descricao, String patrimonio) {
        this.descricao = descricao;
        this.patrimonio = patrimonio;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static ObservableList<ItemComboEquipamento> buscarItems() {

        ObservableList<ItemComboEquipamento> retornar = FXCollections.observableArrayList();
        List<Equipamento> equipamentos = EquipamentoControle.obterListaEquipamentos();

        for (Equipamento it : equipamentos) {
            retornar.add(new ItemComboEquipamento(it.getNome(), it.getPatrimonio()));
        }
        
        return retornar;
    }
}
