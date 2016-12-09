package com.cesco.am3poocriticagastronomica.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cesco on 07/12/16.
 */

public class Critica implements Parcelable {

    private String descricao;
    private String categoria;
    private int avaliacao;
    private String caminhoFoto;
    private String dataCritica;
    private String endereco;

    public Critica() {

    }

    protected Critica(Parcel in) {
        descricao = in.readString();
        categoria = in.readString();
        avaliacao = in.readInt();
        caminhoFoto = in.readString();
        dataCritica = in.readString();
        endereco = in.readString();
    }

    public static final Creator<Critica> CREATOR = new Creator<Critica>() {
        @Override
        public Critica createFromParcel(Parcel in) {
            return new Critica(in);
        }

        @Override
        public Critica[] newArray(int size) {
            return new Critica[size];
        }
    };

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getdataCritica() {
        return dataCritica;
    }

    public void setdataCritica(String dataCritica) {
        this.dataCritica = dataCritica;
    }

    public String getEndereco() { return endereco;}

    public void setEndereco(String endereco) { this.endereco = endereco;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descricao);
        dest.writeString(categoria);
        dest.writeInt(avaliacao);
        dest.writeString(caminhoFoto);
        dest.writeString(dataCritica);
        dest.writeString(endereco);
    }


}

