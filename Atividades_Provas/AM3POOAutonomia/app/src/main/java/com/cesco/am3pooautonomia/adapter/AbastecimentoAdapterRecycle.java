package com.cesco.am3pooautonomia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesco.am3pooautonomia.R;
import com.cesco.am3pooautonomia.modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by cesco on 21/11/16.
 */

public class AbastecimentoAdapterRecycle  extends RecyclerView.Adapter {

    private ArrayList<Abastecimento> listaAbastecimentos;
    private Context context;

    public AbastecimentoAdapterRecycle(Context context, ArrayList<Abastecimento> ab) {
        this.listaAbastecimentos = ab;
        this.context = context;
        //super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_abastecimento, parent, false);
        AbasteciemntoViewHolder retorno = new AbasteciemntoViewHolder(view);
        Log.d("TESTE", "CRIOU UMA CAIXINHA");
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        AbasteciemntoViewHolder caixinha = (AbasteciemntoViewHolder) holder;
        Abastecimento ab = listaAbastecimentos.get(position);
        caixinha.seAtualiza(ab);
        Log.d("TESTE", "ATUALIZOU UMA CAIXINHA");
    }

    @Override
    public int getItemCount() {
        return this.listaAbastecimentos.size();
    }

    public class AbasteciemntoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvData;
        private TextView tvQuilometragem;
        private TextView tvLitros;
        private ImageView ivIconePosto;

        public AbasteciemntoViewHolder(View itemView) {
            super(itemView);
            this.tvData = (TextView) itemView.findViewById(R.id.tvData);
            this.tvQuilometragem = (TextView) itemView.findViewById(R.id.tvQuilometragem);
            this.tvLitros = (TextView) itemView.findViewById(R.id.tvLitros);
            this.ivIconePosto = (ImageView) itemView.findViewById(R.id.ivIconePosto);
        }

        public void seAtualiza(Abastecimento quemDevoMostrar) {

            String txtKm = context.getResources().getString(R.string.textKm);
            String txtMLitros = context.getResources().getString(R.string.textLitros);

            tvData.setText(String.valueOf(quemDevoMostrar.getDataAbastecimento()));
            tvQuilometragem.setText(txtKm+" "+String.valueOf(quemDevoMostrar.getQuilometragem()));
            tvLitros.setText(txtMLitros+" "+String.valueOf(quemDevoMostrar.getLitros()));

            switch (quemDevoMostrar.getPosto()){
                case "Texaco": ivIconePosto.setImageResource(R.drawable.texaco_logo); break;
                case "Shell": ivIconePosto.setImageResource(R.drawable.shell_logo); break;
                case "Petrobras": ivIconePosto.setImageResource(R.drawable.petrobras_logo); break;
                case "Ipiranga": ivIconePosto.setImageResource(R.drawable.ipiranga_logo); break;
                case "Outros": ivIconePosto.setImageResource(R.drawable.outros_logo); break;
            }
        }
    }
}
