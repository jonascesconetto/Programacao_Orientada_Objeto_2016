package com.magnani.introducaointerfaces.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnani.introducaointerfaces.R;
import com.magnani.introducaointerfaces.modelo.Pessoa;

import java.util.ArrayList;

/**
 * Created by marcelo on 11/8/16.
 */
public class PessoaAdapter extends ArrayAdapter {

    private ArrayList<Pessoa> pessoas;

    public PessoaAdapter(Context context, int resource, ArrayList<Pessoa> pessoas) {
        super(context, resource, pessoas);
        this.pessoas = pessoas;
    }

    @Override
    public  View getView(int posicao, View visao, ViewGroup pai){

        Pessoa atual = this.pessoas.get(posicao);

        visao = LayoutInflater.from(getContext()).inflate(R.layout.list_item_pessoa, pai, false);

        TextView tvNome = (TextView) visao.findViewById(R.id.tvNome);
        TextView tvIdade = (TextView) visao.findViewById(R.id.tvIdade);
        ImageView ivIconePessoa = (ImageView) visao.findViewById(R.id.ivIconePessoa);
        tvNome.setText(atual.getNome());
        tvIdade.setText(String.valueOf(atual.getIdade()));

        if(atual.getSexo().equals("F")){
            ivIconePessoa.setImageResource(R.drawable.female);
        }else{
            ivIconePessoa.setImageResource(R.drawable.male);
        }

        return visao;
    }



}
