package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, BusinessNumberField, PrimaryBusinessField, AddressField, ProvinceField;;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        BusinessNumberField = (EditText) findViewById(R.id.BusinessNumber);
        PrimaryBusinessField = (EditText) findViewById(R.id.PrimaryBusiness);
        AddressField = (EditText) findViewById(R.id.Address);
        ProvinceField = (EditText) findViewById(R.id.Province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            BusinessNumberField.setText(receivedPersonInfo.BusinessNumber);
            PrimaryBusinessField.setText(receivedPersonInfo.PrimaryBusiness);
            AddressField.setText(receivedPersonInfo.Address);
            ProvinceField.setText(receivedPersonInfo.Province);
        }
    }

    /**
     * the program can edit more elements for each contact
     * business number
     * address
     * primary business
     * province
     * @param v
     */
    public void updateContact(View v){
        logiFunc logi = new logiFunc();
        /**
         * create a new record
         *  delete the old record
         *  return to main
         */
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String BusinessNumber = BusinessNumberField.getText().toString();
        String PrimaryBusiness = PrimaryBusinessField.getText().toString();
        String Address = AddressField.getText().toString();
        String Province = ProvinceField.getText().toString();
        Contact person = new Contact(personID, name, email,BusinessNumber,PrimaryBusiness,Address,Province);

        if(logi.checkPrimaryBusiness(PrimaryBusiness)==true && logi.checkProvince(Province)==true) {
            appState.firebaseReference.child(personID).setValue(person);
            appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==true && logi.checkProvince(Province)==false){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this,  "Cannot update, your province is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==false && logi.checkProvince(Province)==true){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this,  "Cannot update, your primary business is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        if(logi.checkPrimaryBusiness(PrimaryBusiness)==false && logi.checkProvince(Province)==false){
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this,  "Cannot update, both your province and primary business are incorrect!",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void eraseContact(View v)
    {
        /**
         *delete record
         * return to main menu
         */
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
