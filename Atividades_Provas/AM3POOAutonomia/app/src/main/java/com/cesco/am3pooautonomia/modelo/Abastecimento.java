package com.cesco.am3pooautonomia.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cesco on 20/11/16.
 */

public class Abastecimento implements Parcelable {

    private int quilometragem;
    private float litros;
    private String dataAbastecimento;
    private String posto;

    public Abastecimento() {

    }

    protected Abastecimento(Parcel in) {
        quilometragem = in.readInt();
        litros = in.readFloat();
        dataAbastecimento = in.readString();
        posto = in.readString();
    }

    public static final Creator<Abastecimento> CREATOR = new Creator<Abastecimento>() {
        @Override
        public Abastecimento createFromParcel(Parcel in) {
            return new Abastecimento(in);
        }

        @Override
        public Abastecimento[] newArray(int size) {
            return new Abastecimento[size];
        }
    };


    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public float getLitros() {
        return litros;
    }

    public void setLitros(float litros) {
        this.litros = litros;
    }

    public String getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(String dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quilometragem);
        dest.writeFloat(litros);
        dest.writeString(dataAbastecimento);
        dest.writeString(posto);
    }
}
