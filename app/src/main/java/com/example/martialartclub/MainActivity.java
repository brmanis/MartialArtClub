package com.example.martialartclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.example.martialartclub.Model.DatabaseHandler;
import com.example.martialartclub.Model.MartialArt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseHandler databaseHandler;
    private double totalMarialArtPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMarialArtPrice =0.0;
        scrollView = findViewById(R.id.scrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;
        modifyUserInterface();


    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> AllMartialArtsObjects =
                databaseHandler.returnAllMartialArtObjects();
        scrollView.removeAllViewsInLayout();

        if(AllMartialArtsObjects.size()>0){
            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((AllMartialArtsObjects.size()+1)/2);
            gridLayout.setColumnCount(2);
            MartialArtButton[] martialArtButtons =
                    new MartialArtButton[AllMartialArtsObjects.size()];
            int index=0;
            for(MartialArt martialArtObject: AllMartialArtsObjects){
                martialArtButtons[index]=new MartialArtButton(
                        MainActivity.this, martialArtObject);
                martialArtButtons[index].setText(martialArtObject.getMartialArtId() + "\n" + martialArtObject.getMartialArtName() + "\n" + martialArtObject.getMartialArtPrice());
                switch(martialArtObject.getMartialArtColor()){
                    case "BLUE":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                        case "RED":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                        case "CYAN":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "YELLOW":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "GRAY":
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);
                        break;
                        case  "BLACK":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        martialArtButtons[index].setTextColor(Color.WHITE);
                        break;
                        case "GREEN":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                }
            martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index],
                        martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            scrollView.addView(gridLayout);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.Add_Martial_Art:
                Intent addMartialArtIntent= new Intent(MainActivity.this, AddMartialArt.class);
                startActivity(addMartialArtIntent);
                return true;

            case R.id.Delete_Martial_Art:
                Intent deleteMartialArtIntent = new Intent(MainActivity.this, DeleteMartialArt.class);
                startActivity(deleteMartialArtIntent);
                return true;

                case R.id.Update_Martial_Art:
                Intent updateMartialArtIntent = new Intent(MainActivity.this, Update_MartialArt.class);
                startActivity(updateMartialArtIntent);
                return true;
            case R.id.Reset_Martial_Art:
                totalMarialArtPrice = 0.0;
                Toast.makeText(MainActivity.this,totalMarialArtPrice + "",Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {

        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMarialArtPrice=totalMarialArtPrice + martialArtButton.getMartialArtPrice();
        String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().
                format(totalMarialArtPrice);
        Toast.makeText(MainActivity.this, martialArtPriceFormatted,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyUserInterface();
    }
}