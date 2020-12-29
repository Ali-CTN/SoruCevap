package com.example.SinavSavaslari;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.SinavSavaslari.Common.Common;
import com.example.SinavSavaslari.Interface.ItemClickListener;
import com.example.SinavSavaslari.Model.Category;
import com.example.SinavSavaslari.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    View myFragment;

    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder>adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

        public static CategoryFragment newInstence(){
            CategoryFragment categoryFragment= new CategoryFragment();
            return categoryFragment;



        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Kategori");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = (RecyclerView)myFragment.findViewById(R.id.list_categpry);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategories();

        return myFragment;
    }

    private void loadCategories() {
            adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                    Category.class,
                    R.layout.category_layout,
                    CategoryViewHolder.class,
                    categories
            ) {

                @Override
                protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {
                    viewHolder.category_name.setText(model.getName());
                    

                     viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Intent startGame=new Intent(getActivity(),Start.class);
                            Common.categoryId=adapter.getRef(position).getKey();
                            Common.categoryName=model.getName();
                            startActivity(startGame);
                        }
                    });

                }
            };
            adapter.notifyDataSetChanged();
            listCategory.setAdapter(adapter);

    }


}
