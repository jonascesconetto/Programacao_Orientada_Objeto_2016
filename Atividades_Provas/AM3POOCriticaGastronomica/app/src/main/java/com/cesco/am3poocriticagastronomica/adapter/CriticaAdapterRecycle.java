package com.cesco.am3poocriticagastronomica.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesco.am3poocriticagastronomica.R;
import com.cesco.am3poocriticagastronomica.modelo.Critica;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cesco on 07/12/16.
 */

public class CriticaAdapterRecycle extends RecyclerView.Adapter {

    private ArrayList<Critica> listaCriticas;
    private Context context;

    public CriticaAdapterRecycle(Context context, ArrayList<Critica> ab) {
        this.listaCriticas = ab;
        this.context = context;
        //super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_criticas, parent, false);
        CriticaViewHolder retorno = new CriticaViewHolder(view);
        Log.d("TESTE", "CRIOU UMA CAIXINHA");
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CriticaViewHolder caixinha = (CriticaViewHolder) holder;
        Critica ab = listaCriticas.get(position);
        caixinha.seAtualiza(ab);
        Log.d("TESTE", "ATUALIZOU UMA CAIXINHA");
    }

    @Override
    public int getItemCount() {
        return this.listaCriticas.size();
    }

    public class CriticaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFoodPicture;
        private TextView tvDescricao;
        private TextView tvCategoria;
        // avaliação e data concatenada
        private TextView tvDataContatenadoAvaliacao;

        public CriticaViewHolder(View itemView) {
            super(itemView);
            this.tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            this.tvCategoria = (TextView) itemView.findViewById(R.id.tvCategoria);
            this.ivFoodPicture = (ImageView) itemView.findViewById(R.id.ivFoodPicture);
            this.tvDataContatenadoAvaliacao = (TextView) itemView.findViewById(R.id.tvDataContatenadoAvaliacao);
        }

        public void seAtualiza(Critica quemDevoMostrar) {

            //categoria
            tvCategoria.setText(quemDevoMostrar.getCategoria());
            //descricao
            tvDescricao.setText(quemDevoMostrar.getDescricao());
            //avaliacao e data
            String textAvaliacaoData = context.getResources().getString(R.string.textAvaliacaoDataConcatenados, quemDevoMostrar.getAvaliacao(), quemDevoMostrar.getdataCritica());
            tvDataContatenadoAvaliacao.setText(textAvaliacaoData);
            //imagem
            if(quemDevoMostrar.getCaminhoFoto() != null){
                //a pessoa tem fotografia
                File arquivo = new File(quemDevoMostrar.getCaminhoFoto());
                ivFoodPicture.setImageURI(Uri.fromFile(arquivo));
            }else{
                //não tem foto, usar imagens aleatórias
                ivFoodPicture.setImageResource(R.drawable.image_logo);
            }
        }
    }
}

