package com.mobile.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.application.models.Course;
import com.mobile.application.models.Order;

import java.util.ArrayList;
import java.util.List;

public class page_of_order extends AppCompatActivity {
    static List<String> title_of_courses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_of_order);

        ListView list_of_orders=findViewById(R.id.list_of_orders);
        ImageView imageView =findViewById(R.id.imageView_empty_basket);
        TextView textView =findViewById(R.id.text_empty_basket);

        for(Course c : MainActivity.courseList_full){
            if(Order.basket_courses.contains(c.getId()))
            {
                title_of_courses.add(c.getTitle());
            }
        }
        if(title_of_courses.isEmpty())
        {
            list_of_orders.setVisibility(list_of_orders.INVISIBLE);
            imageView.setVisibility(imageView.VISIBLE);
            textView.setVisibility(textView.VISIBLE);
        }
        else
        {
            list_of_orders.setVisibility(list_of_orders.VISIBLE);
            imageView.setVisibility(imageView.INVISIBLE);
            textView.setVisibility(textView.INVISIBLE);
            list_of_orders.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, title_of_courses));
        }

    }
    public void button_clear_list(View view)
    {
        ListView list_of_orders=findViewById(R.id.list_of_orders);
        ImageView imageView =findViewById(R.id.imageView_empty_basket);
        TextView textView =findViewById(R.id.text_empty_basket);
        Order.basket_courses.clear();
        title_of_courses.clear();
        list_of_orders.setVisibility(list_of_orders.INVISIBLE);
        imageView.setVisibility(imageView.VISIBLE);
        textView.setVisibility(textView.VISIBLE);
    }
}