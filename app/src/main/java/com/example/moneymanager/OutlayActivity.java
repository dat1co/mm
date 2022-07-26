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

import com.google.firebase.firestore.FirebaseFirestore;

public class OutlayActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button bankbut;
    Button menubut;
    Button outbut;
    Button moneybut;
    TextView moneytxt;
    EditText userinput;
    private int finalvalue;
    SharedPreferences pref;

    final static String userVariableKey = "USER_VARIABLE";
    final Context context = this;
    private final String save_key = "save_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlay);
        bankbut = (Button) findViewById(R.id.bankbut);
        menubut = (Button) findViewById(R.id.menubut);
        outbut = (Button) findViewById(R.id.outbut);
        moneybut = (Button) findViewById(R.id.moneybut);
        bankbut.setOnClickListener(this::OpenBankActivity);
        menubut.setOnClickListener(this::OpenMainActivity);
        moneybut.setOnClickListener(this::OpenMoneyActivity);
        moneytxt = (TextView) findViewById(R.id.money2);
        userinput = (EditText) findViewById(R.id.editTextNumber);
        init();


        outbut.setOnClickListener(new View.OnClickListener() {

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
                                finalvalue -= additionalValue;
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


    public void updateFinalValue(){

        SharedPreferences.Editor edit = pref.edit();
        edit.putString(save_key, "" + finalvalue);
        edit.apply();
        moneytxt.setText(pref.getString(save_key,"0"));
    }

    public void OpenMainActivity (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenMoneyActivity (View v){
        Intent intent = new Intent(this, MoneyActivity.class);
        startActivity(intent);
    }

    public void OpenBankActivity (View v){
        Intent intent = new Intent(this, BankActivity.class);
        startActivity(intent);
    }

    private void init(){
        pref = getSharedPreferences("money", MODE_PRIVATE);
        moneytxt.setText(pref.getString(save_key,""));
    }

}