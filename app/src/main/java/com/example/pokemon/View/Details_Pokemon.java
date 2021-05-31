package com.example.pokemon.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon.Adapter.EvolutionAdapter;
import com.example.pokemon.Adapter.TypeAdapter;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;


public class Details_Pokemon extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name, pokemon_height,pokemon_weight,pokemon_candy,pokemon_candy_count,pokemon_egg,pokemon_avgspawn,pokemon_spawnchance,pokemon_spawntime;
    RecyclerView recycler_type,recycler_weakness,recycler_next_evolution,recycler_prev_evolution;
    CardView cardView_name,cardview_type,cardview_weakness,cardview_prev_evolution, cardview_next_evolution;

    static Details_Pokemon instance;

    //Check instance has value or not
    public static Details_Pokemon getInstance(){
        if(instance == null){
            instance = new Details_Pokemon();
        }
        return instance;
    }
    public Details_Pokemon() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_pokemon_details, container, false);

        //Get position from argument
        Pokemon pokemon;

        //Check argument
        if(getArguments().get("num") == null){
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        }
        else {
            pokemon = Common.findPokemonByNum(getArguments().getString("num"));
        }
        //Initialize Fields
        pokemon_img = (ImageView)itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.name);
        pokemon_height = (TextView) itemView.findViewById(R.id.height);
        pokemon_weight = (TextView) itemView.findViewById(R.id.weight);
        pokemon_candy = (TextView) itemView.findViewById(R.id.candy);
        pokemon_candy_count = (TextView) itemView.findViewById(R.id.candy_count);
        pokemon_egg = (TextView) itemView.findViewById(R.id.egg);
        pokemon_avgspawn = (TextView) itemView.findViewById(R.id.average_spawn);
        pokemon_spawnchance = (TextView) itemView.findViewById(R.id.spawn_chance);
        pokemon_spawntime = (TextView) itemView.findViewById(R.id.spawn_time);
        cardView_name = (CardView) itemView.findViewById(R.id.card_view_text_name);
        cardview_type = (CardView) itemView.findViewById(R.id.card_view_text_type);
        cardview_weakness = (CardView) itemView.findViewById(R.id.card_view_text_weekness);
        cardview_prev_evolution = (CardView) itemView.findViewById(R.id.card_view_text_prevevolution);
        cardview_next_evolution = (CardView) itemView.findViewById(R.id.card_view_text_nextevolution);


        //Recycler view settings
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

        //Get Values from pokemon file
        setDetailsPokemon(pokemon);


        return itemView;
    }

    private void setDetailsPokemon(Pokemon pokemon) {
        //Load Image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);
        //Load Data from JSON File
        pokemon_name.setText("Name : " +pokemon.getName());
        pokemon_weight.setText("Weight : " +pokemon.getWeight());
        pokemon_height.setText("Height : " +pokemon.getHeight());
        pokemon_candy.setText("Candy :  " +pokemon.getCandy());
        pokemon_candy_count.setText("Candy Count : " +pokemon.getCandy_count());
        pokemon_egg.setText("Egg : " +pokemon.getEgg());
        pokemon_avgspawn.setText("Average Spawn : " +pokemon.getAvg_spawns());
        pokemon_spawntime.setText("Spawn Time : " +pokemon.getSpawn_time());
        pokemon_spawnchance.setText("Spawn Chance : " +pokemon.getSpawn_chance());
        cardView_name.setBackgroundResource(R.drawable.cardview_style); //set cardview border
        cardview_type.setBackgroundResource(R.drawable.cardview_style); //set cardview border
        cardview_weakness.setBackgroundResource(R.drawable.cardview_style); //set cardview border
        cardview_prev_evolution.setBackgroundResource(R.drawable.cardview_style); //set cardview border
        cardview_next_evolution.setBackgroundResource(R.drawable.cardview_style); //set cardview border

        //Set Type
        TypeAdapter typeAdapter = new TypeAdapter(getActivity(),pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //Set Weakness
        TypeAdapter weaknessAdapter = new TypeAdapter(getActivity(),pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);

        //Set Evolution
        EvolutionAdapter prevEvolutionAdapter = new EvolutionAdapter(getActivity(),pokemon.getPrev_evolution());
        recycler_prev_evolution.setAdapter(prevEvolutionAdapter);

        //Set Evolution
        EvolutionAdapter nextEvolutionAdapter = new EvolutionAdapter(getActivity(),pokemon.getNext_evolution());
        recycler_next_evolution.setAdapter(nextEvolutionAdapter);


    }
}