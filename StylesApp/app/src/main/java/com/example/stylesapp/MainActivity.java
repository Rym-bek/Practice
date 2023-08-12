package com.example.stylesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //обявление небходимых виджетов
    Button button, button_reset;
    AppCompatTextView textView;
    EditText textInputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //проверка был ли запрос на обновление темы
        if(getIntent().getBooleanExtra("refresh",false))
        {
            //обновление темы
            setTheme(R.style.newTheme);
        }
        //присвоить необходимый файл отображения
        setContentView(R.layout.activity_main);

        //найти необходимые элементы управления
        button=findViewById(R.id.button);
        button_reset=findViewById(R.id.button_reset);
        textView=findViewById(R.id.textView);
        textInputEditText=findViewById(R.id.textInputEditText);
        //проверить был ли запрос на обновление стиля текста
        if(getIntent().getBooleanExtra("refresh",false))
        {
            //обновление стиля текста
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.CustomTextViewStyle);
            }
        }

        //кнопка обновления страницы
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //запуск текущей страницы по новой с флагом true
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.putExtra("refresh",true);
                startActivity(intent);
            }
        });

        //кнопка сброса страницы
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //запуск текущей страницы по новой с флагом false
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.putExtra("refresh",false);
                startActivity(intent);
            }
        });

    }
}