package com.example.pokemon.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Interface.IItemClickListener;
import com.example.pokemon.R;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder> {

    Context context;
    List<String> typeList;

    public TypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TypeAdapter.MyViewHolder holder, int position) {

        //Set Values in the chip from JSON file
        holder.chip.setChipText(typeList.get(position));
        if(holder.chip == null){
            holder.chip.setChipText("Null");
        }
        holder.chip.setChipBackgroundColor(ColorStateList.valueOf(Common.getColorByType(typeList.get(position))));
        try {
            holder.setiItemClickListener(new IItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    //Fix Crash
                }
            });
        }
        catch(Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(View itemView){
            super(itemView);
            //Initialize and click listener for chip item
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iItemClickListener.onClick(view,getAdapterPosition());
                }
            });
        }
    }
}
