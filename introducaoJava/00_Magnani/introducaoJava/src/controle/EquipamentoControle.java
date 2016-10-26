/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Date;
import modelo.Equipamento;
import modelo.EquipamentoDao;
import modelo.Manutencao;
import modelo.ManutencaoDao;

/**
 *
 * @author marcelo
 */
public class EquipamentoControle {
    
    public static void receberDadosCadastroEquipamento(String nome, String patrimonio, Date dataAquisicao, Date dateTeminoGarantia, float valor){
        Equipamento objetoEquipamento = new Equipamento();
        objetoEquipamento.setNome(nome);
        objetoEquipamento.setPatrimonio(patrimonio);
        objetoEquipamento.setDataAquisicao(dataAquisicao);
        objetoEquipamento.setDataTerminoGarantia(dateTeminoGarantia);
        objetoEquipamento.setValor(valor);
        EquipamentoDao.salvar(objetoEquipamento);
    }
    
    public static ArrayList<Equipamento> obterListaEquipamentos(){
        return EquipamentoDao.obterLista();
    }
    
    public static Equipamento obterEquipamentoPeloNumeroDoPatrimonio(String numeroPatrimonio){
        return EquipamentoDao.obterPeloNumero(numeroPatrimonio);
    }
    
    public static void receberDadosNovaManutencao(String numeroPatrimonio, String descricao, Date data, float valor){
        Equipamento encontrado = EquipamentoDao.obterPeloNumero(numeroPatrimonio);
        Manutencao novaManutencao = new Manutencao();
        novaManutencao.setData(data);
        novaManutencao.setDescricao(descricao);
        novaManutencao.setValor(valor);
        encontrado.adicionaManutencao(novaManutencao);
        ManutencaoDao.salvar(encontrado);
    }
    
}
