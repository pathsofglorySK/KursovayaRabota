package com.example.kursovaya_kislukhin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovaya_kislukhin.BookPage;
import com.example.kursovaya_kislukhin.R;
import com.example.kursovaya_kislukhin.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    List<Book> books;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.books = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bookItems = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(bookItems);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.book_Title.setText(books.get(position).getTitle());
        holder.authorName.setText(books.get(position).getAuthor());
        holder.publicationYear.setText(books.get(position).getPublicationYear());
        holder.genre.setText(books.get(position).getGenre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookPage.class);
                intent.putExtra("bookTitle",books.get(position).getTitle());
                intent.putExtra("bookAuthor",books.get(position).getAuthor());
                intent.putExtra("bookYear",books.get(position).getPublicationYear());
                intent.putExtra("bookGenre",books.get(position).getGenre());
                intent.putExtra("bookComment",books.get(position).getComment());
                intent.putExtra("bookLanguage",books.get(position).getLanguage());
                intent.putExtra("bookPages",books.get(position).getPages());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder {

        TextView book_Title;
        TextView authorName;
        TextView publicationYear;
        TextView genre;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            book_Title = itemView.findViewById(R.id.book_Title);
            authorName = itemView.findViewById(R.id.authorName);
            publicationYear = itemView.findViewById(R.id.publicationYear);
            genre = itemView.findViewById(R.id.genre);
        }
    }
}
