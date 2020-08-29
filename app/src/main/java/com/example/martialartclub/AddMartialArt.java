package com.example.martialartclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martialartclub.Model.DatabaseHandler;
import com.example.martialartclub.Model.MartialArt;

public class AddMartialArt extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPrice, edtColor;
    private Button btnAddMartialArt, btnBack;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);
        Toast.makeText(AddMartialArt.this,"This is Add Martial art Activity",Toast.LENGTH_SHORT).show();

        edtName=findViewById(R.id.edtName);
        edtPrice=findViewById(R.id.edtPrice);
        edtColor=findViewById(R.id.edtColor);
        btnAddMartialArt=findViewById(R.id.btnAddMartial);
        btnBack=findViewById(R.id.btnGoBack);

        databaseHandler = new DatabaseHandler(AddMartialArt.this);
        btnAddMartialArt.setOnClickListener(AddMartialArt.this);
        btnBack.setOnClickListener(this);

    }

    private void addMartialArtObjectToDatabase(){
        String nameValue= edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try{
            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(0, nameValue,
                    priceDoubleValue, colorValue);
            databaseHandler.addMartialArt(martialArtObject);
            Toast.makeText(AddMartialArt.this,martialArtObject+ "Martial Art Objects is added to Database",
                    Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddMartial:
                addMartialArtObjectToDatabase();
                break;
            case R.id.btnGoBack:
                this.finish();
                break;
        }
    }
}