package com.example.pokemon.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.Common.Common;
import com.example.pokemon.Interface.IItemClickListener;
import com.example.pokemon.R;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder> {

    Context context;
    List<String> typeList;

    public PokemonEvolutionAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @NotNull
    @Override
    public PokemonEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);


        return new PokemonEvolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PokemonEvolutionAdapter.MyViewHolder holder, int position) {

        holder.chip.setChipText(typeList.get(position));
        holder.chip.setChipBackgroundColor(ColorStateList.valueOf(Common.getColorByType(typeList.get(position))));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        IItemClickListener iItemClickListener;
        public MyViewHolder(View itemView){
            super(itemView);
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
