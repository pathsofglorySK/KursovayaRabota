package com.example.kursovaya_kislukhin;

import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_NAME;
import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_VERSION;
import static com.example.kursovaya_kislukhin.DBHelper.TABLE_BOOKS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kursovaya_kislukhin.adapter.BookAdapter;
import com.example.kursovaya_kislukhin.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button settingsButton;
    private ImageButton newBookButton;
    RecyclerView bookRecycler;
    BookAdapter bookAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, TABLE_BOOKS, DATABASE_VERSION);
        List<Book> bookList = new ArrayList<>();
//        bookList.add(new Book(10,"книга","автор", "фентези","2021","комментарий","russian","190"));
//        setBookRecycler(bookList);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_BOOKS, null, null, null, null, null , null, null);
        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int idTitle = cursor.getColumnIndex(DBHelper.KEY_TITLE);
            int idAuthor = cursor.getColumnIndex(DBHelper.KEY_AUTHOR);
            int idDate = cursor.getColumnIndex(DBHelper.KEY_DATE);
            int idGenre = cursor.getColumnIndex(DBHelper.KEY_GENRE);
            int idComment = cursor.getColumnIndex(DBHelper.KEY_COMMENT);
            int idPages = cursor.getColumnIndex(DBHelper.KEY_PAGES);
            int idLanguage = cursor.getColumnIndex(DBHelper.KEY_LANGUAGE);

            do {
                int id = cursor.getInt(idIndex);
                String title = cursor.getString(idTitle);
                String author = cursor.getString(idAuthor);
                String date = cursor.getString(idDate);
                String genre = cursor.getString(idGenre);
                String comment = cursor.getString(idComment);
                String pages = cursor.getString(idPages);
                String language = cursor.getString(idLanguage);

                bookList.add(new Book(id, title, author, genre, date, comment, language, pages));
            } while (cursor.moveToNext());
            setBookRecycler(bookList);
        }

        cursor.close();
        dbHelper.close();

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_settings();
            }
        });

        newBookButton = (ImageButton) findViewById(R.id.newBookButton);
        newBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBookActivity();
            }
        });
    }
    public void openActivity_settings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openAddBookActivity(){
        Intent intent = new Intent(this, AddBook.class);
        startActivity(intent);
    }


    public void setBookRecycler(List<Book> bookList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        bookRecycler = findViewById(R.id.bookRecycler);
        bookRecycler.setLayoutManager(layoutManager);

        bookAdapter = new BookAdapter(this, bookList);
        bookRecycler.setAdapter(bookAdapter);

    }
}