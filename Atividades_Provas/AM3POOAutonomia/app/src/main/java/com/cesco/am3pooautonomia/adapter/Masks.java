package com.cesco.am3pooautonomia.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by cesco on 21/11/16.
 */

public abstract class Masks {
    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            public void onTextChanged(CharSequence s, int start, int before,int count) {

                String str = Masks.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }
}

//    Adapter Masks
//    final EditText campo_data_nascimento = (EditText) findViewById(R.id.campo_data_nascimento);
//campo_data_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_data_nascimento));
//
//final EditText campo_cpf = (EditText) findViewById(R.id.campo_cpf);
//        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));
//
//final EditText campo_telefone = (EditText) findViewById(R.id.campo_telefone);
//        campo_telefone.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone));