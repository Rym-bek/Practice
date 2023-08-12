package com.mobile.application.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.application.Page_of_course;
import com.mobile.application.R;
import com.mobile.application.models.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems= LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.course_background.setCardBackgroundColor(Color.parseColor(courses.get(position).getColor()));
        int image_id = context.getResources().getIdentifier("ic_"+courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.course_image.setImageResource(image_id);
        holder.course_title.setText(courses.get(position).getTitle());
        holder.course_date.setText(courses.get(position).getDate());
        holder.course_level.setText((courses.get(position).getLevel()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Page_of_course.class);

                ActivityOptions options  = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View, String>(holder.course_image, "course_image")
                        );

                intent.putExtra("course_background", Color.parseColor(courses.get(position).getColor()));
                intent.putExtra("course_image", image_id);
                intent.putExtra("course_title", courses.get(position).getTitle());
                intent.putExtra("course_date", courses.get(position).getDate());
                intent.putExtra("course_level",courses.get(position).getLevel());
                intent.putExtra("course_text",courses.get(position).getText());
                intent.putExtra("course_id",courses.get(position).getId());

                context.startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        CardView course_background;
        ImageView course_image;
        TextView course_title, course_date, course_level;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            course_image=itemView.findViewById(R.id.course_image);
            course_title=itemView.findViewById(R.id.course_title);
            course_date=itemView.findViewById(R.id.course_date);
            course_level=itemView.findViewById(R.id.course_level);
            course_background=itemView.findViewById(R.id.course_background);

        }
    }
}
