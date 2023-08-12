package com.mobile.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobile.application.adapter.AdapterOfCategory;
import com.mobile.application.adapter.CourseAdapter;
import com.mobile.application.models.Category;
import com.mobile.application.models.Course;
import com.mobile.application.models.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, course_recycler;
    AdapterOfCategory categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> courseList_full = new ArrayList<>();

    private Database_helper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new Database_helper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        List<Category> categoryList = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM categories;", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String course_category = cursor.getString(1);
            categoryList.add(new Category(id,course_category));
        }
        cursor.close();

        setCategoryRecycler(categoryList);


        Cursor cursor_courses = mDb.rawQuery(
                "Select * FROM courses INNER JOIN levels ON levels.id = courses.course_level", null);
        while(cursor_courses.moveToNext()){
            int id = cursor_courses.getInt(0);
            String course_image = cursor_courses.getString(1);
            String course_title = "Профессия "+cursor_courses.getString(2)+"\nразработчик";
            String course_date = cursor_courses.getString(3);
            String course_level = cursor_courses.getString(9);
            int course_category = cursor_courses.getInt(5);
            String course_color  = cursor_courses.getString(6);
            String course_info  = cursor_courses.getString(7);
            courseList.add(new Course(id,course_image,course_title,course_date,course_level,course_category,course_color,course_info));
        }
        Log.d("suka_suka_suka", Arrays.toString(courseList.toArray()));
        cursor_courses.close();
        courseList_full.addAll(courseList);

        setCourseRecycler(courseList);
    }

    public void open_basket(View view){
        Intent intent = new Intent(this, page_of_order.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        course_recycler = findViewById(R.id.course_recycler);
        course_recycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        course_recycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new AdapterOfCategory(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
    public void showAnyCourse(View view)
    {
        courseList.clear();
        courseList.addAll(courseList_full);
        courseAdapter.notifyDataSetChanged();
    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(courseList_full);

        List<Course> list_filter_of_courses = new ArrayList<>();

        for(Course c:courseList)
        {
            if(c.getCategory() == category)
            {
                list_filter_of_courses.add(c);
            }
        }

        courseList.clear();
        courseList.addAll(list_filter_of_courses);

        courseAdapter.notifyDataSetChanged();
    }
}