package com.mobile.application.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.application.MainActivity;
import com.mobile.application.R;
import com.mobile.application.models.Category;

import java.util.List;

public class AdapterOfCategory extends RecyclerView.Adapter<AdapterOfCategory.CategoryViewHolder> {


    Context context;
    List<Category> categories;

    public AdapterOfCategory(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems= LayoutInflater.from(context).inflate(R.layout.item_of_category, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfCategory.CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryTitle.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showCoursesByCategory(categories.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {

        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;
        public CategoryViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
