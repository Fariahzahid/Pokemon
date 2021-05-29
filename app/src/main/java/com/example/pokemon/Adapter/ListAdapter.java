package com.example.pokemon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Interface.IItemClickListener;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonList;

    public ListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ListAdapter.MyViewHolder holder, int position) {

        try {
            //Load Image
            Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);        }
        catch(Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }


        //Set Name
        holder.pokemon_name.setText(pokemonList.get(position).getName());
        //Event for item click in list
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num",pokemonList.get(position).getNum()));

            }
        });

        holder.pokemon_height.setText(pokemonList.get(position).getHeight());
        //Event listener
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num",pokemonList.get(position).getNum()));

            }
        });
        holder.pokemon_weight.setText(pokemonList.get(position).getWeight());
        //Event Listener
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(context,"Click at Pokemon : " +pokemonList.get(position).getName(),Toast.LENGTH_LONG).show();
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num",pokemonList.get(position).getNum()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;
        TextView pokemon_height;
        TextView pokemon_weight;


        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);

            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView)itemView.findViewById(R.id.txt_pokemon_name);
            pokemon_height = (TextView)itemView.findViewById(R.id.txt_pokemon_height);
            pokemon_weight = (TextView)itemView.findViewById(R.id.txt_pokemon_weight);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            iItemClickListener.onClick(view,getAdapterPosition());
        }
    }
}
