package com.example.moneymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button billbutton;
    Button outlaybutton;
    Button bankbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billbutton = (Button)findViewById(R.id.billbutton);
        outlaybutton = (Button)findViewById(R.id.outlaybutton);
        bankbutton = (Button)findViewById(R.id.bankbutton);
        billbutton.setOnClickListener(this::openMoneyActivity);
        outlaybutton.setOnClickListener(this::openOutlayActivity);
        bankbutton.setOnClickListener(this::openBankActivity);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Title");
        alert.setMessage("Message");
    }



    public void openMoneyActivity(View v) {
        Intent intent = new Intent(this, MoneyActivity.class);
        startActivity(intent);
    }

    public void  openOutlayActivity(View v) {
        Intent intent = new Intent(this,OutlayActivity.class);
        startActivity(intent);
    }

    public void  openBankActivity(View v) {
        Intent intent = new Intent(this,BankActivity.class);
        startActivity(intent);
    }

}