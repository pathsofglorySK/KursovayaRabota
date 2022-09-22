package com.example.kursovaya_kislukhin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookPage extends AppCompatActivity {

    private Button settingsButton;
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_page);
        TextView bookTitle = findViewById(R.id.bookPageName2);
        TextView bookAuthor = findViewById(R.id.bookPageAuthor2);
        TextView bookData = findViewById(R.id.bookPageData2);
        TextView bookGenre = findViewById(R.id.bookPageGenre2);
        TextView bookPageAmount = findViewById(R.id.bookPagePageAmount2);
        TextView bookLanguage = findViewById(R.id.bookPageLanguage2);
        TextView bookCommentText = findViewById(R.id.bookPageCommentText);

        bookTitle.setText(getIntent().getStringExtra("bookTitle"));
        bookAuthor.setText(getIntent().getStringExtra("bookAuthor"));
        bookData.setText(getIntent().getStringExtra("bookYear"));
        bookGenre.setText(getIntent().getStringExtra("bookGenre"));
        bookPageAmount.setText(getIntent().getStringExtra("bookPages"));
        bookLanguage.setText(getIntent().getStringExtra("bookLanguage"));
        bookCommentText.setText(getIntent().getStringExtra("bookComment"));

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_settings();
            }
        });
        mainButton = (Button) findViewById(R.id.mainPageButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_main();
            }
        });
    }
    public void openActivity_settings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);

    }
    public void openActivity_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}