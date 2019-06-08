package com.infamous_software.kboard;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem searchMenuItem = menu.findItem(R.id.type);
        SearchView searchViewAction = (SearchView) MenuItemCompat.getActionView(searchMenuItem);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        searchViewAction.setSearchableInfo(searchableInfo);
        searchViewAction.setIconifiedByDefault(true);

        //String suggestWord = "Sempiternal";
        //searchViewAction.setQuery(suggestWord, false);
        searchViewAction.setQueryHint("Test here");
        searchViewAction.clearFocus();

        ImageView searchMagIcon = searchViewAction.findViewById(android.support.v7.appcompat.R.id.search_button);
        searchMagIcon.setImageResource(R.drawable.icon_type);

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.about) {
            Toast.makeText(this, "v2.2", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
