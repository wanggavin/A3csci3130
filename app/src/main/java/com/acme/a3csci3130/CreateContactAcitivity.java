package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, BusinessNumberField, PrimaryBusinessField, AddressField, ProvinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        BusinessNumberField = (EditText) findViewById(R.id.BusinessNumber);
        PrimaryBusinessField = (EditText) findViewById(R.id.PrimaryBusiness);
        AddressField = (EditText) findViewById(R.id.Address);
        ProvinceField = (EditText) findViewById(R.id.Province);
    }

    /**
     * add four elements for every contact
     * business number
     * address
     * province
     * primary
     * @param v
     */
    public void submitInfoButton(View v) {

        logiFunc logi = new logiFunc();

        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();

        String BusinessNumber = BusinessNumberField.getText().toString();
        String PrimaryBusiness = PrimaryBusinessField.getText().toString();
        String Address = AddressField.getText().toString();
        String Province = ProvinceField.getText().toString();
        Contact person = new Contact(personID, name, email, BusinessNumber, PrimaryBusiness, Address, Province);

        if(logi.checkPrimaryBusiness(PrimaryBusiness)==true && logi.checkProvince(Province)==true)
        appState.firebaseReference.child(personID).setValue(person);
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==true && logi.checkProvince(Province)==false){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this,  "Cannot create, your province is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==false && logi.checkProvince(Province)==true){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this,  "Cannot create, your primary business is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==false && logi.checkProvince(Province)==false){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this,  "Cannot create, both your province and primary business are incorrect!",
                    Toast.LENGTH_LONG).show();
        }

        finish();

    }
}
