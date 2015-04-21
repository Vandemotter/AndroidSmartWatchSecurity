package com.example.paul.infosec;

import android.app.Fragment;


public class BookListActivity extends SingleFragmentActivity {
    private static final String BOOK = "book";

    @Override
    protected Fragment createFragment() {
        return new BookListFragment();
    }


    /*


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.book_array, menu);
       // return true;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_actions, menu);
        return super.onCreateOptionsMenu(menu);



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
            dialog.show(fm, BOOK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    */



}
