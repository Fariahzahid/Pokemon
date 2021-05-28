package com.example.pokemon.Retrofit;

import com.example.pokemon.Model.Pokedex;

import io.reactivex.Observable;

import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("cfxSFgvWxu?indent=2")
    Observable<Pokedex> getListPokemon();

}
