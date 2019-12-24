package com.example.orator;


import android.content.Context;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.nio.file.Files.readAllLines;

//TODO//Перед использованием добавьте файлы для проэкта
// Текстовые файлы должны распологаться в папке assets/inputdata !!!

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView outputPhraseView;
    ArrayList<ArrayList<String>> db = new ArrayList<>(); //Решение создать массив массивов выглядит оптимальным для
    // универсальности, т.к. в теории можно добавлять необходимое
    // количество файлов, и удлиннять текст сколько угодно
    InputStream is;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        startButton = findViewById(R.id.start);
        outputPhraseView=findViewById(R.id.outputPhrase);
//        Log.i("Testing", "Loaded");

//        String[] f = new String[0];
//        try {
//            f = getAssets().list("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for(String f1 : f){
//            Log.i("Testing",f1);
//        }

        dataPreparator();

//        testChamber();

    }

    void dataPreparator() {

        int length=0; //Создаем переменную для подсчета количества файлов в необходимой папке.
        Log.i("Testing", "Data Preparator started");

        try {
            length = getAssets().list("inputdata").length;
            Log.i("Testing", ""+length);

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i = 1; i<length; i++){

            try {
                is=context.getAssets().open("inputdata/db"+i+".txt");
                Log.i("Testing", "FIS SET"+i);
            } catch (IOException e) {

                e.printStackTrace();
                Log.i("Testing", "NOT WORKING");
                return;
            }
//            if()
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-16LE")));
                Log.i("Testing", "BufferedReader ready");

                ArrayList<String> arrayList=new ArrayList<>();
                Log.i("Testing", "AL created reading file");

                while (br.ready()) {
                    arrayList.add(br.readLine());
//                    Log.i("Testing", result.get(result.size()-1));

                }
                Log.i("Testing", "Array list Ready");
                db.add(arrayList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    StringBuilder generatingPhrase(){
        StringBuilder phrase=new StringBuilder("");

        for(ArrayList<String> al:db){
            phrase.append(al.get(new Random().nextInt(al.size()))+" ");
        }
        return phrase;
    }//создаем фразу из массивов в порядке очередности

    public void onClick(View view) {
        outputPhraseView.setText(generatingPhrase());
    }




}



