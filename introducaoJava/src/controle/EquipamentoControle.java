/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import armazenamento.MeioArmazenamento;
import java.util.ArrayList;
import java.util.Date;
import modelo.Equipamento;
import modelo.Manutencao;

/**
 *
 * @author jonas
 */
public class EquipamentoControle {
    public static void receberDadosCadstroEquipamentos(String nome, String patrimonio, Date dataAquisicao, Date dataTerminoGarantia, float valor){
        //precisamos criar nosso modelo para podermos instanciar nossos objetos
        
        Equipamento objetoEquipamento = new Equipamento();
        //atribuição dos parametros para avariavel
        objetoEquipamento.setNome(nome);
        objetoEquipamento.setPatrimonio(patrimonio);
        objetoEquipamento.setDataAquisicao(dataAquisicao);
        objetoEquipamento.setDataTerminoGarantia(dataTerminoGarantia);
        objetoEquipamento.setValor(valor);
        //persistencia de arquivo em uma lista ==>>SALVAR<<==
        objetoEquipamento.salvar();    
        
    }
    
    public static ArrayList<Equipamento> obterListaEquipamentos(){
        //obtenho uma lista dos equipamentos
        return Equipamento.obterLista();
    }
    
    public static Equipamento obterEquipamentoPeloNumeroDoPatrimonio(String numeroPatrimonio){
        return Equipamento.obterPeloNumero(numeroPatrimonio);
    }
    
    public static void receberDadosNovaManutencao(String numeroPatrimonio, String descricao, Date data, float valor){
        Equipamento encontrado = Equipamento.obterPeloNumero(numeroPatrimonio);
        Manutencao novaManutencao = new Manutencao();
        novaManutencao.setData(data);
        novaManutencao.setDescricao(descricao);
        novaManutencao.setValor(valor);
        //adicionar o objeto encapsulado em uma posição do vetor manutenções de um equipamento (selecionado)
        encontrado.adicionaManutencao(novaManutencao);
    }
    
}
