package com.magnani.introducaointerfaces.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcelo on 11/3/16.
 */
public class Pessoa implements Parcelable {

    private String nome;
    private String sobrenome;
    private int idade;
    private String sexo;
    private String caminhoFoto;


    public Pessoa() {

    }

    protected Pessoa(Parcel in) {
        nome = in.readString();
        sobrenome = in.readString();
        idade = in.readInt();
        sexo = in.readString();
        caminhoFoto = in.readString();
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(sobrenome);
        dest.writeInt(idade);
        dest.writeString(sexo);
        dest.writeString(caminhoFoto);
    }

    public String toString(){
        return "Eu sou o "+this.getNome();
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
