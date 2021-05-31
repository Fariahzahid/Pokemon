package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pokemon.View.View_Pokemon;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnimation, bottomAnimation;
    ImageView splashscreen_pokemon_image,splashscreen_pokemon_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Set Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        splashscreen_pokemon_image = findViewById(R.id.splash_screen_pokemon_image);
        splashscreen_pokemon_logo = findViewById(R.id.splash_screen_pokemon_logo);

        //Add animation to the image
        splashscreen_pokemon_image.setAnimation(topAnimation);
        splashscreen_pokemon_logo.setAnimation(bottomAnimation);

        //Navigate to next Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, View_Pokemon.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }


}
