package com.magnani.introducaointerfaces.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnani.introducaointerfaces.R;
import com.magnani.introducaointerfaces.modelo.Pessoa;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by marcelo on 11/10/16.
 */
public class PessoaAdapterRecycle extends RecyclerView.Adapter {

    private ArrayList<Pessoa> listaPessoas;
    private Context context;

    public PessoaAdapterRecycle(Context c, ArrayList<Pessoa> p){
        this.listaPessoas = p;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_pessoa_card, parent, false);
        PessoaViewHolder retorno = new PessoaViewHolder(view);
        Log.d("TESTE", "CRIOU UMA CAIXINHA");
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PessoaViewHolder caixinha = (PessoaViewHolder) holder;
        Pessoa p = listaPessoas.get(position);
        caixinha.seAtualiza(p);
        Log.d("TESTE", "ATUALIZOU UMA CAIXINHA");
    }

    @Override
    public int getItemCount() {
        return this.listaPessoas.size();
    }

    public class PessoaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIconePessoa;
        private TextView tvNome;
        private TextView tvIdade;

        public PessoaViewHolder(View itemView) {
            super(itemView);

            this.ivIconePessoa = (ImageView) itemView.findViewById(R.id.ivIconePessoa);
            this.tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            this.tvIdade = (TextView) itemView.findViewById(R.id.tvIdade);

        }

        public void seAtualiza(Pessoa quemDevoMostrar) {
            tvNome.setText(quemDevoMostrar.getNome());
            tvIdade.setText("Gerador de bla bl abla. bla bldas dlsa dsakjdha lkj hlkah dlakjh dlakjhd alkjdhs");
            int sorteio = (int) (Math.random() * 10);
            if(sorteio % 3 == 0){
                ivIconePessoa.setImageResource(R.drawable.imagem1);
            }
            if(sorteio % 3 == 1){
                ivIconePessoa.setImageResource(R.drawable.imagem2);
            }
            if(sorteio % 3 == 2){
                ivIconePessoa.setImageResource(R.drawable.imagem3);
            }
        }

    }

}
