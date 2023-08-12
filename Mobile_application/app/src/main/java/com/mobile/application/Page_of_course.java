package com.mobile.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.application.models.Order;


public class Page_of_course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_of_course);

        ConstraintLayout course_background = findViewById(R.id.page_of_course_background);
        ImageView course_image=findViewById(R.id.page_of_course_image);
        TextView course_title=findViewById(R.id.page_of_course_title);
        TextView course_date=findViewById(R.id.page_of_course_date);
        TextView course_level=findViewById(R.id.page_of_course_level);
        TextView course_text=findViewById(R.id.page_of_course_text);
        course_background.setBackgroundColor(getIntent().getIntExtra("course_background",0));
        course_image.setImageResource(getIntent().getIntExtra("course_image",0));
        course_title.setText(getIntent().getStringExtra("course_title"));
        course_date.setText(getIntent().getStringExtra("course_date"));
        course_level.setText(getIntent().getStringExtra("course_level"));
        course_text.setText(getIntent().getStringExtra("course_text"));
    }
    public void add_to_basket(View view){
        int item_id = getIntent().getIntExtra("course_id",0);
        Order.basket_courses.add(item_id);
        Toast.makeText(Page_of_course.this, "Добавлено в корзину", Toast.LENGTH_LONG).show();
    }


}