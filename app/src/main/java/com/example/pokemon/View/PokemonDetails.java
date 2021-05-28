package com.example.pokemon.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon.Adapter.PokemonTypeAdapter;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;


public class PokemonDetails extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name, pokemon_height,pokemon_weight;
    RecyclerView recycler_type,recycler_weakness,recycler_next_evolution,recycler_prev_evolution;

    static PokemonDetails instance;

    public static PokemonDetails getInstance(){
        if(instance == null){
            instance = new PokemonDetails();


        }
        return instance;
    }
    public PokemonDetails() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_pokemon_details, container, false);


        Pokemon pokemon;
        //Get position from argument
        if(getArguments().get("num") == null){
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        }
        else {
            pokemon = null;
        }
        pokemon_img = (ImageView)itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.name);
        pokemon_height = (TextView) itemView.findViewById(R.id.height);
        pokemon_weight = (TextView) itemView.findViewById(R.id.weight);

        recycler_type = (RecyclerView) itemView.findViewById(R.id.recycler_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recycler_weakness = (RecyclerView) itemView.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        recycler_prev_evolution = (RecyclerView) itemView.findViewById(R.id.recycler_prev_evolution);
        recycler_prev_evolution.setHasFixedSize(true);
        recycler_prev_evolution.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recycler_next_evolution = (RecyclerView) itemView.findViewById(R.id.recycler_next_evolution);
        recycler_next_evolution.setHasFixedSize(true);
        recycler_next_evolution.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        setDetailsPokemon(pokemon);


        return itemView;
    }

    private void setDetailsPokemon(Pokemon pokemon) {
        //Load Image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);
        pokemon_name.setText(pokemon.getName());
        pokemon_weight.setText("Weight " +pokemon.getWeight());
        pokemon_height.setText("Height " +pokemon.getHeight());

        //Set Type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //Set Weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        recycler_weakness.setAdapter(typeAdapter);

    }
}