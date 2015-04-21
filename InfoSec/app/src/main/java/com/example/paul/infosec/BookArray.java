package com.example.paul.infosec;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by paul on 11/12/14.
 */
public class BookArray {
    private ArrayList<Book> Books;
    private Context AppContext;

    private static BookArray bookArray;

    private BookArray(Context appContext) {
        AppContext = appContext;
        Books = new ArrayList<Book>();

        /*

        for (int i = 0; i < 10; i++) {
            Book c = new Book();
            c.setTitle("Book" + i);
            Books.add(c);
        }

        */
    }


    public static BookArray get(Context c) {
        if (bookArray == null) {
            bookArray = new BookArray(c.getApplicationContext());
        }
        return bookArray;
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public Book getBook(UUID id) {
        for (Book c : Books) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void AddBook(String name) {
        Book b = new Book();
        b.setTitle(name);
        Books.add(b);

    }
}
