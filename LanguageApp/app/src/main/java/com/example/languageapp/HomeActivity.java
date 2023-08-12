package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    //объявить необходимые виджеты
    Toolbar toolbar_home;
    TextView title,description;
    Context context;

    //Метод onCreate, в нём выполняется весь код
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //присвоить файл представления
        setContentView(R.layout.home_activity);
        //получить виджеты по их id
        toolbar_home=findViewById(R.id.toolbar_home);
        context=getBaseContext();
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);

        //объявить тулбар, это верхняя шапка приложения
        setSupportActionBar(toolbar_home);
        //объявить кнопку "назад"
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_home.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    //главная функция, она изменяет язык
    private void setLanguage(String languageToLoad)
    {
        //получить язык по коду
        Locale locale = new Locale(languageToLoad);
        //получить сделать его предпочтительным
        Locale.setDefault(locale);
        //присвоить конфигурации текущий язык
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        //отправить запрос на обновление локализации
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        //обновить данные
        title.setText(R.string.title);
        description.setText(R.string.description);
        toolbar_home.setTitle(R.string.app_name);
    }

    //присовить меню
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //обновить исходя из выборанного языка
        switch(item.getItemId()){
            //казахский
            case R.id.action_selectKazakh:
                setLanguage("kk");
                return true;
            //русский
            case R.id.action_selectRussian:
                setLanguage("ru");
                return true;
            //английский
            case R.id.action_selectEnglish:
                setLanguage("en");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}