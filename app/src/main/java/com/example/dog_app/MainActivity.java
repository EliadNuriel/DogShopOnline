package com.example.dog_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dog_app.models.Dog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity implements DogItemClickListener {

    private RecyclerView dogsRv;
    private DogsRvAdapter dogsRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton add_button = findViewById(R.id.add_new_dog_button);
        FloatingActionButton about_button = findViewById(R.id.about_us_button);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(i);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddDogActivity.class);
                startActivity(i);
            }
        });
        dogsRv = findViewById(R.id.dogs_rv);
        dogsRv.setAdapter(dogsRvAdapter);

        dogsRv.setLayoutManager(new LinearLayoutManager(this));

        ExternalStore.getAllDogs(dogs -> {
            if (dogsRvAdapter != null) {
                dogsRvAdapter.changeDataSet(dogs);
                return;
            }
            dogsRvAdapter = new DogsRvAdapter(dogs, this);
            dogsRv.setAdapter(dogsRvAdapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickedShowMore(Dog dog) {
        Intent i = new Intent(this, DogDetailsActivity.class);
        Gson g = new Gson();
        i.putExtra("dog",g.toJson(dog));
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExternalStore.removeDogListener();
    }

    @Override
    public void clickedDelete(Dog dog) {
        ExternalStore.removeDogFromStorage(dog, s ->
                        Toast.makeText(this, s, Toast.LENGTH_SHORT).show(),
                e -> Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}