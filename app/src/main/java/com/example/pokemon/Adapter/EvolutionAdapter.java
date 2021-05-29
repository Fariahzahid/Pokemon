package com.example.pokemon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.Common.Common;
import com.example.pokemon.Model.Evolution;
import com.example.pokemon.R;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EvolutionAdapter extends RecyclerView.Adapter<EvolutionAdapter.MyViewHolder> {

    Context context;
    List<Evolution> evolutions;

    public EvolutionAdapter(Context context, List<Evolution> evolutions) {
        this.context = context;

        if(evolutions != null) {
            this.evolutions = evolutions;
        }
        else
            //Fix crash of pokemon evolution not exists
            this.evolutions = new ArrayList<>();

    }

    @NonNull
    @NotNull
    @Override
    public EvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new EvolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EvolutionAdapter.MyViewHolder holder, int position) {

        //Chip Values of evolution with item listener
        holder.chip.setChipText(evolutions.get(position).getName());
        holder.chip.setChipBackgroundColor(
                ColorStateList.valueOf(Common.getColorByType(
                        Common.findPokemonByNum(
                                evolutions.get(position).getNum()
                        ).getType().get(0)
                ))
        );

    }

    @Override
    public int getItemCount() {
        return evolutions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        public MyViewHolder(View itemView){
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            //Event
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(new Intent(Common.KEY_NUM_EVOLUTION).putExtra("num",evolutions.get(getAdapterPosition()).getNum()));
                }
            });
        }
    }
}
