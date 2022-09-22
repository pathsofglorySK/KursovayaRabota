package com.example.kursovaya_kislukhin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mainButton = (Button) findViewById(R.id.mainPageButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_main();
            }
        });

    }
    public void openActivity_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}