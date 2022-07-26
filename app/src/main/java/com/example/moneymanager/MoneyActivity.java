package com.example.moneymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoneyActivity extends AppCompatActivity {
    DatabaseReference mDataBase;
    Button addmoney;
    Button mainbutton;
    Button bankbutton;
    Button outlaybutton;
    int moneyt;
    public TextView moneytext;
    SharedPreferences pref;
    EditText ed1;
    int finalvalue;

    final Context context = this;
    private final String save_key = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        addmoney = (Button)findViewById(R.id.addmoney);
        outlaybutton = (Button)findViewById(R.id.outlayButton);
        bankbutton = (Button)findViewById(R.id.bankButton);
        mainbutton = (Button) findViewById(R.id.mainbutton);
        mainbutton.setOnClickListener(this::openmainactivity);
        bankbutton.setOnClickListener(this::openbankactivity);
        outlaybutton.setOnClickListener(this::openoutlayactivity);
        moneytext = (TextView) findViewById(R.id.money);
        ed1 = (EditText) findViewById(R.id.editTextNumber);
        init();



        addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);


                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
                mDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextNumber);
                mDialogBuilder.setCancelable(false);
                mDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                int additionalValue = Integer.parseInt(userInput.getText().toString());
                                int money = Integer.parseInt(moneytext.getText());
                                money=finalvalue;
                                finalvalue += additionalValue;
                                updateFinalValue();

                            }
                        });
                mDialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = mDialogBuilder.create();
                alertDialog.show();


            }
        });
    }



    public static final String APP_PREFERENCES_MONEY = "money";

    public void updateFinalValue(){

        SharedPreferences.Editor edit = pref.edit();
        edit.putString(save_key, ""+finalvalue);
        edit.apply();
        moneytext.setText(pref.getString(save_key,"0"));



    }



    public void openmainactivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openoutlayactivity(View v){
        Intent intent = new Intent(this, OutlayActivity.class);
        startActivity(intent);
    }

    public void openbankactivity(View v){
        Intent intent = new Intent(this, BankActivity.class);
        startActivity(intent);
    }
    private void init(){
        pref = getSharedPreferences("money", MODE_PRIVATE);
        moneytext.setText(pref.getString(save_key,""));
    }

    private void finalvalue(String string) {
    }

}
