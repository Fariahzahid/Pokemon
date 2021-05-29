package com.example.pokemon.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pokemon.Common.Common;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;

import java.net.NetworkInterface;

public class View_Pokemon extends AppCompatActivity {

    Toolbar toolbar;

    //Showing Fragments
    BroadcastReceiver showDetailsPokemon = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                //Enable Back Button At the BAck
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                //Replace Fragment
                Fragment detailFragment = Details_Pokemon.getInstance();
                String num = intent.getStringExtra("num");
                Bundle bundle = new Bundle();
                bundle.putString("num",num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("details");
                fragmentTransaction.commit();

                //Set Pokemone Name for toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };


    BroadcastReceiver showEvolutionPokemon = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_NUM_EVOLUTION)){

                //Replace Fragment
                Fragment detailFragment = Details_Pokemon.getInstance();
                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num",num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment); //Remove current Fragment
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("details");
                fragmentTransaction.commit();

                //Set Pokemone Name for toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMON");
        setSupportActionBar(toolbar);


        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetailsPokemon,new IntentFilter(Common.KEY_ENABLE_HOME));
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolutionPokemon,new IntentFilter(Common.KEY_NUM_EVOLUTION));

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case android.R.id.home:
                toolbar.setTitle("Pokemon");

                //Clear All fragment details and pop to list fragment
                getSupportFragmentManager().popBackStack("details", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
        }
        return true;
    }

}