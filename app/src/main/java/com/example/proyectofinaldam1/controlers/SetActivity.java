package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectofinaldam1.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class SetActivity extends AppCompatActivity {

    private ImageView imgJP1;
    private ImageView imgJP2;

    private ImageView imgBg;

    private Spinner spIcon;

    private List<Integer> imgIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        this.imgJP1 = (ImageView) findViewById(R.id.imgsetPlayer1);
        this.imgJP1 = (ImageView) findViewById(R.id.imgsetPlayer1);

        this.imgBg = (ImageView) findViewById(R.id.setStage);

        this.spIcon = (Spinner) findViewById(R.id.spIcon);

        imgIcons = new ArrayList<>();

        createList();

        spIcon.setAdapter(adapter);
    }

    private void createList() {
        Resources res = getResources();
        Field[] drawableFields = R.drawable.class.getFields();
        for (Field field: drawableFields) {
            try {
                if (field.getName().startsWith("chr")){
                    int resId = field.getInt(null);
                    imgIcons.add(resId);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, imgIcons) {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(getItem(position));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100)); // Cambia el tamaño según sea necesario
            return imageView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    };
}