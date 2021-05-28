package com.example.pokemon.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pokemon.Common.Common;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;

public class PokemonView extends AppCompatActivity {

    Toolbar toolbar;

    BroadcastReceiver showDetails = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                //Enable Back Button At the BAck
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                //Replace Fragment
                Fragment detailFragment = PokemonDetails.getInstance();
                int position = intent.getIntExtra("position",-1);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("details");
                fragmentTransaction.commit();

                //Set Pokemone NAme for toolbar
                Pokemon pokemon = Common.commonPokemonList.get(position);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMON LIST");
        setSupportActionBar(toolbar);

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetails,new IntentFilter(Common.KEY_ENABLE_HOME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case android.R.id.home:
                toolbar.setTitle("POKEMON LIST");

                //Clear All fragment details and pop to list fragment
                getSupportFragmentManager().popBackStack("details", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
        }
        return true;
    }

}