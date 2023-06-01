package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.models.AnimatedCharapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.os.Bundle;
import android.os.Handler;
public class SetActivity extends AppCompatActivity {

    private ImageView imgJP1;
    private ImageView imgJP2;
    private ImageView imgBg;
    private Spinner spIcon;
    private List<Integer> imgIcons;
    private ArrayAdapter<Integer> adapter;
    private Handler handlerJP1;
    private Button btnSelectScene;

    private AnimatedCharapter animeChrJP1;
    private AnimatedCharapter animeChrJP2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        animeChrJP1 = new AnimatedCharapter(new int[]{R.drawable.hidle1,R.drawable.hidle2,R.drawable.hidle3,R.drawable.hidle4,R.drawable.hidle5},
                new int[]{R.drawable.hrun1,R.drawable.hrun2,R.drawable.hrun3,R.drawable.hrun4,R.drawable.hrun5},
                new int[]{R.drawable.hattack1,R.drawable.hattack2,R.drawable.hattack3,R.drawable.hattack4,R.drawable.hattack5},
                new int[]{R.drawable.hdeath1,R.drawable.hdeath2,R.drawable.hdeath3,R.drawable.hdeath4,R.drawable.hdeath5},
                R.drawable.hshield);
        this.imgJP1 = (ImageView) findViewById(R.id.imgsetPlayer1);
        this.imgJP1 = (ImageView) findViewById(R.id.imgsetPlayer1);

        this.imgBg = (ImageView) findViewById(R.id.setStage);

        this.spIcon = (Spinner) findViewById(R.id.spIcon);
        this.btnSelectScene = (Button)  findViewById(R.id.btnSelectScene);
        btnSelectScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animeChrJP1.incrPostStatus();
            }
        });

        imgIcons = new ArrayList<>();
        handlerJP1 = new Handler();
        createList();
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, imgIcons) {
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
        spIcon.setAdapter(adapter);


        spIcon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int img = Integer.parseInt(parent.getItemAtPosition(position).toString());
                if (img != R.drawable.chrrandom){

                }else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Runnable run = new Runnable() {
            @Override
            public void run() {
                animeChrJP1.changeImageSet(new AnimatedCharapter.SetListImg() {
                    @Override
                    public void setListChr(int[] listImg, int index) {
                        if(listImg.length != 5){
                            imgJP1.setImageResource(listImg[0]);
                        }else{
                            imgJP1.setImageResource(listImg[index]);
                        }
                    }
                });
                handlerJP1.postDelayed(this, 150);
            }
        };
        handlerJP1.postDelayed(run, 150);
    }
    private int indice = 0;
    private void createList() {
        Resources res = getResources();
        Field[] drawableFields = R.drawable.class.getFields();
        imgIcons.add(R.drawable.chrrandom);
        for (Field field: drawableFields) {
            try {
                if (field.getName().startsWith("chr") && !(field.getName().startsWith("chrrandom"))){
                    int resId = field.getInt(null);
                    imgIcons.add(resId);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}