package com.example.paul.infosec;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class BookListFragment extends ListFragment {
    private ArrayList<Book> Books;
    private Book Book;
    private static final String TAG = "BookListFragment";
    private static final String BOOK = "book";
    public static final int REQUEST_BOOK = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Passwords");
        Books = BookArray.get(getActivity()).getBooks();

        BookAdapter adapter = new BookAdapter(Books);
        setHasOptionsMenu(true);
        setListAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((BookAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_BOOK) {
            String title = data.getStringExtra(CreateBookDialog.EXTRA_TITLE);
            String username = data.getStringExtra(CreateBookDialog.EXTRA_USERNAME);
            String password = data.getStringExtra(CreateBookDialog.EXTRA_PASSWORD);
            Book c = new Book();
            c.setTitle(title);
            c.setUsername(username);
            c.setPassword(password);

            Books.add(c);
            ((BookAdapter) getListAdapter()).notifyDataSetChanged();
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Book c = ((BookAdapter)getListAdapter()).getItem(position);

    }

    private class BookAdapter extends ArrayAdapter<Book> {
        public BookAdapter(ArrayList<Book> books) {
            super(getActivity(), 0, books);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_book, null);
            }
            // Configure the view for this Book
            Book c = getItem(position);

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView usernameTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_usernameTextView);
            usernameTextView.setText(c.getUsername());

            TextView passwordTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_passwordTextView);
            passwordTextView.setText(c.getPassword());

            return convertView;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.book_array, menu);
        // return true;

        inflater.inflate(R.menu.book_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.create_book) {
            //open dialog
            FragmentManager fm = getFragmentManager();
            CreateBookDialog dialog = new CreateBookDialog();
            dialog.setTargetFragment(BookListFragment.this, REQUEST_BOOK);
            dialog.show(fm, BOOK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
