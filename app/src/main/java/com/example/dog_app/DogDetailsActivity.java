package com.example.dog_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dog_app.models.Dog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DogDetailsActivity extends AppCompatActivity {
    ImageView dogImageView;
    TextView dogNameTv, dogBreedTv, dogAgeTv, dogPriceTv;
    TextView dogVaccineTv, dogGenderTv;
    Button buttonBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);
        dogImageView = findViewById(R.id.add_dog_image);
        dogNameTv = findViewById(R.id.details_dog_name);
        dogBreedTv = findViewById(R.id.dog_breed_tv);
        dogAgeTv = findViewById(R.id.dog_age_tv);
        dogPriceTv = findViewById(R.id.dog_price_tv);
        dogGenderTv = findViewById(R.id.details_dog_gender_tv);
        dogVaccineTv = findViewById(R.id.details_vaccine_tv);
        buttonBack = findViewById(R.id.details_button_back);
        Dog dog;
        Intent intent = getIntent();
        if (intent != null) {
             String dogString = intent.getStringExtra("dog");
             dog = new Gson().fromJson(dogString,Dog.class);
            Picasso.get().load(dog.getImage()).into(dogImageView);
            dogNameTv.setText(dog.getName());
            TextView details = findViewById(R.id.dog_name_details);
            details.setText(dog.getName());
            dogBreedTv.setText("Breed: " + dog.getBreed());
            dogAgeTv.setText("Age: " + dog.getAge());
            dogPriceTv.setText("Price: " + dog.getPrice());
            dogGenderTv.setText("Gender: " + dog.getGender());
            dogVaccineTv.setText(dog.isVaccinated() ? "Vaccinated" : "Not Vaccinated");
            buttonBack.setOnClickListener(v -> finish());
        }
    }
}
