package com.example.martialartclub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martialartclub.Model.DatabaseHandler;
import com.example.martialartclub.Model.MartialArt;

import java.util.ArrayList;

public class Update_MartialArt extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__martial_art);

        databaseHandler = new DatabaseHandler(Update_MartialArt.this);
        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArtObjects =
                databaseHandler.returnAllMartialArtObjects();
        if(martialArtObjects.size()>0){
            ScrollView scrollView = new ScrollView(Update_MartialArt.this);
            GridLayout gridLayout = new GridLayout(Update_MartialArt.this);
            gridLayout.setRowCount(martialArtObjects.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextView = new TextView[martialArtObjects.size()];
            EditText[][] edtNamesPricesColors =
                    new EditText[martialArtObjects.size()][3];
            Button[] Modifybuttons = new Button[martialArtObjects.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for(MartialArt martialArtObject: martialArtObjects){

                idTextView[index] = new TextView(Update_MartialArt.this);
                idTextView[index].setGravity(Gravity.CENTER);
                idTextView[index].setText(martialArtObject.getMartialArtId() + "");

                edtNamesPricesColors[index][0]=new EditText(Update_MartialArt.this);
                edtNamesPricesColors[index][1]=new EditText(Update_MartialArt.this);
                edtNamesPricesColors[index][2]=new EditText(Update_MartialArt.this);

                edtNamesPricesColors[index][0].setText(martialArtObject.getMartialArtName());
                edtNamesPricesColors[index][1].setText(martialArtObject.getMartialArtPrice() +"");
                edtNamesPricesColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamesPricesColors[index][2].setText(martialArtObject.getMartialArtColor());

                edtNamesPricesColors[index][0].setId(martialArtObject.getMartialArtId() + 10);
                edtNamesPricesColors[index][1].setId(martialArtObject.getMartialArtId() + 20);
                edtNamesPricesColors[index][2].setId(martialArtObject.getMartialArtId() + 30);

                Modifybuttons[index] = new Button(Update_MartialArt.this);
                Modifybuttons[index].setText("MODIFY");
                Modifybuttons[index].setId(martialArtObject.getMartialArtId());
                Modifybuttons[index].setOnClickListener(Update_MartialArt.this);

                gridLayout.addView(idTextView[index], (int) (screenWidth * 0.05),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][0],
                        (int)(screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][1],
                        (int)(screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][2],
                        (int)(screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(Modifybuttons[index],
                        (int)(screenWidth * 0.35), ViewGroup.LayoutParams.WRAP_CONTENT);
                index ++;
            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);


        }

    }

    @Override
    public void onClick(View view) {
        int martialArtObjectID = view.getId();
        EditText edtMartialArtName = findViewById(martialArtObjectID +10);
        EditText edtMartialArtPrice = findViewById(martialArtObjectID +20);
        EditText edtMartialArtColor = findViewById(martialArtObjectID +30);

        String martialArtNameStringValue = edtMartialArtName.getText().toString();
        String martialArtPriceStringValue = edtMartialArtPrice.getText().toString();
        String martialArtColorStringValue = edtMartialArtColor.getText().toString();

        try{
            double martialArtPriceDoubleValue =
                    Double.parseDouble(martialArtPriceStringValue);
            databaseHandler.modifyMartialArtObject(martialArtObjectID,
                    martialArtNameStringValue,
                    martialArtPriceDoubleValue,
                    martialArtColorStringValue);
            Toast.makeText(Update_MartialArt.this,
                    "This Martial Art Object is updated",Toast.LENGTH_SHORT).show();

        }catch(NumberFormatException e ){

        }




    }
}