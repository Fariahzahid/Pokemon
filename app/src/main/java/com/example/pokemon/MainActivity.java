package com.example.pokemon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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

        //Check Internet Connection
        if(!isConnected(this)){
            showCustomDialog();
        }
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
    private boolean isConnected(MainActivity view_pokemon) {
        ConnectivityManager connectivityManager = (ConnectivityManager) view_pokemon.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifiConnection != null && wifiConnection.isConnected() || mobileConnection!=null && mobileConnection.isConnected()){
            return true;
        }
        else
            return false;



    }
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Please Connect to the Internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), View_Pokemon.class));
                        finish();
                    }
                });

    }

}
