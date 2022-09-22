package com.example.kursovaya_kislukhin;

import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_NAME;
import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_VERSION;
import static com.example.kursovaya_kislukhin.DBHelper.TABLE_BOOKS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.kursovaya_kislukhin.adapter.BookAdapter;
import com.example.kursovaya_kislukhin.model.Book;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity {

    private Button settingsButton;
    private Button mainButton;
    private Button addBookButton;
    private EditText addBookTitle;
    private EditText addBookAuthor;
    private EditText addBookPublicationDate;
    private EditText addBookGenre;
    private EditText addBookPagesAmount;
    private EditText addBookLanguage;
    private ImageButton closeButton;
    BookAdapter bookAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this, DATABASE_NAME, DATABASE_VERSION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
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

        closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_main();
            }
        });
        addBookTitle = (EditText) findViewById(R.id.addBookTitle);
        addBookAuthor = (EditText) findViewById(R.id.addBookAuthor);
        addBookPublicationDate = (EditText) findViewById(R.id.addBookPublicationDate);
        addBookGenre = (EditText) findViewById(R.id.addBookGenre);
        addBookPagesAmount = (EditText) findViewById(R.id.addBookPagesAmount);
        addBookLanguage = (EditText) findViewById(R.id.addBookLanguage);

        addBookButton = (Button) findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                String title = addBookTitle.getText().toString();
                String author = addBookAuthor.getText().toString();
                String publication_date = addBookPublicationDate.getText().toString();
                String genre = addBookGenre.getText().toString();
                String pages = addBookPagesAmount.getText().toString();
                String language = addBookLanguage.getText().toString();
                String comment = "comment";

                contentValues.put(DBHelper.KEY_TITLE, title);
                contentValues.put(DBHelper.KEY_AUTHOR, author);
                contentValues.put(DBHelper.KEY_DATE, publication_date);
                contentValues.put(DBHelper.KEY_GENRE, genre);
                contentValues.put(DBHelper.KEY_PAGES, pages);
                contentValues.put(DBHelper.KEY_LANGUAGE, language);
                contentValues.put(DBHelper.KEY_COMMENT, comment);

                database.insert(DBHelper.TABLE_BOOKS, null, contentValues);

                dbHelper.close();

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