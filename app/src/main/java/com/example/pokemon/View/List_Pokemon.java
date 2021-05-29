package com.example.pokemon.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.functions.Consumer;

import com.example.pokemon.Adapter.ListAdapter;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Common.ItemOffsetDecoration;
import com.example.pokemon.Model.Pokedex;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;
import com.example.pokemon.Retrofit.IPokemonDex;
import com.example.pokemon.Retrofit.RetrofitClient;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class List_Pokemon extends Fragment {

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    ListAdapter adapter,search_adapter;

    List<String> last_suggest = new ArrayList<>();
    MaterialSearchBar searchBar;

    static List_Pokemon instance;

    public static List_Pokemon getInstance(){
        if(instance == null){
            instance = new List_Pokemon();
        }
        return instance;

    }
    //Get values from Retrofit File
    public List_Pokemon() {
        Retrofit retrofit = RetrofitClient.getInstace();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        //Set recycler View containing list
        pokemon_list_recyclerview = (RecyclerView)view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        //Setup SearchBar
        searchBar = (MaterialSearchBar)view.findViewById(R.id.searchBar);
        searchBar.setHint("Enter Pokemon Name");
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList<>();
                for(String search:last_suggest){
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    //Return default adapter
                    pokemon_list_recyclerview.setAdapter(adapter);
                }

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        //Method for fetching data from the Json file
        fetchData();
        return view;
    }

    private void startSearch(CharSequence text) {
        if(Common.commonPokemonList.size()>0){
            List<Pokemon> result = new ArrayList<>();
            for(Pokemon pokemon: Common.commonPokemonList){
                if(pokemon.getName().toLowerCase().contains(text.toString().toLowerCase())){
                    result.add(pokemon);
                }
            }
            search_adapter = new ListAdapter(getActivity(),result);
            pokemon_list_recyclerview.setAdapter(search_adapter);
        }
    }


    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        adapter = new ListAdapter(getActivity(),Common.commonPokemonList);
                        pokemon_list_recyclerview.setAdapter(adapter);
                        last_suggest.clear();

                        for(Pokemon pokemon:Common.commonPokemonList){
                            last_suggest.add(pokemon.getName());
                        }
                        //Display search bar after loading all pokemon from the API
                        searchBar.setVisibility(View.VISIBLE);
                        searchBar.setLastSuggestions(last_suggest);

                    }
                })
        );
    }
}