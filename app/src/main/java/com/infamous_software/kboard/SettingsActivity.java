package com.infamous_software.kboard;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Button buttonNormalKeyColor;
    Button buttonPressedKeyColor;
    Button buttonFontKeyColor;

    Switch switchNumberRow;
    Switch switchKeyPreview;
    Switch switchKeypressVibration;
    Switch switchKeypressSound;

    ImageView imageViewNormalKeyColor;
    ImageView imageViewPressedKeyColor;
    ImageView imageViewFontKeyColor;

    boolean stateSwitchNumberRow;
    boolean stateSwitchKeyPreview;
    boolean stateSwitchKeypressVibration;
    boolean stateSwitchKeypressSound;

    String normalKeyColor;
    String pressedKeyColor;
    String fontKeyColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        buttonNormalKeyColor = findViewById(R.id.buttonNormalKeyColor);
        buttonPressedKeyColor = findViewById(R.id.buttonPressedKeyColor);
        buttonFontKeyColor = findViewById(R.id.buttonFontKeyColor);

        imageViewNormalKeyColor = findViewById(R.id.img_normal_key_color);
        imageViewPressedKeyColor = findViewById(R.id.img_pressed_key_color);
        imageViewFontKeyColor = findViewById(R.id.img_font_key_color);

        stateSwitchNumberRow = sharedPreferences.getBoolean("switchNumberRow",false);
        switchNumberRow = findViewById(R.id.switch_number_row);
        switchNumberRow.setChecked(stateSwitchNumberRow);
        switchNumberRow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putBoolean("switchNumberRow", isChecked).apply();
            }
        });

        stateSwitchKeyPreview = sharedPreferences.getBoolean("switchKeyPreview",false);
        switchKeyPreview = findViewById(R.id.switch_key_preview);
        switchKeyPreview.setChecked(stateSwitchNumberRow);
        switchKeyPreview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putBoolean("switchKeyPreview", isChecked).apply();
            }
        });

        stateSwitchKeypressVibration = sharedPreferences.getBoolean("switchKeypressVibration",false);
        switchKeypressVibration = findViewById(R.id.switch_keypress_vibration);
        switchKeypressVibration.setChecked(stateSwitchKeypressVibration);
        switchKeypressVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putBoolean("switchKeypressVibration", isChecked).apply();
            }
        });

        stateSwitchKeypressSound = sharedPreferences.getBoolean("switchKeypressSound",false);
        switchKeypressSound = findViewById(R.id.switch_keypress_sound);
        switchKeypressSound.setChecked(stateSwitchKeypressSound);
        switchKeypressSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putBoolean("switchKeypressSound", isChecked).apply();
            }
        });

        buttonNormalKeyColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickNormalKeyColor();
            }
        });

        buttonPressedKeyColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPressedKeyColor();
            }
        });

        buttonFontKeyColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFontKeyColor();
            }
        });
    }


    public void pickFontKeyColor(){
        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Pick color font");
        builder.setPreferenceName("color_font");
        builder.setPositiveButton("Confirm", new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                fontKeyColor = "#"+colorEnvelope.getColorHtml();
                Toast.makeText(getApplicationContext(), fontKeyColor, Toast.LENGTH_LONG).show();
                imageViewFontKeyColor.setBackgroundColor(Color.parseColor(fontKeyColor));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void pickPressedKeyColor(){
        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Pick a pressed key color font");
        builder.setPreferenceName("color_pressed_key");
        builder.setPositiveButton("Confirm", new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                pressedKeyColor = "#"+colorEnvelope.getColorHtml();
                Toast.makeText(getApplicationContext(), pressedKeyColor, Toast.LENGTH_LONG).show();
                imageViewPressedKeyColor.setBackgroundColor(Color.parseColor(pressedKeyColor));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void pickNormalKeyColor(){
        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Pick a normal key color font");
        builder.setPreferenceName("color_normal_key");
        builder.setPositiveButton("Confirm", new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                normalKeyColor = "#"+colorEnvelope.getColorHtml();
                Toast.makeText(getApplicationContext(), normalKeyColor, Toast.LENGTH_LONG).show();
                imageViewNormalKeyColor.setBackgroundColor(Color.parseColor(normalKeyColor));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
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
