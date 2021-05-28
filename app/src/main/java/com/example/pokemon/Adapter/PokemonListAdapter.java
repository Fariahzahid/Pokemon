package com.example.pokemon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Interface.IItemClickListener;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
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
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PokemonListAdapter.MyViewHolder holder, int position) {
        //Load Image
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);
        //Set Name
        holder.pokemon_name.setText(pokemonList.get(position).getName());
        //Event
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(context,"Click at Pokemon : " +pokemonList.get(position).getName(),Toast.LENGTH_LONG).show();
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("position",position));

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

        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);

            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView)itemView.findViewById(R.id.txt_pokemon_name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            iItemClickListener.onClick(view,getAdapterPosition());
        }
    }
}
