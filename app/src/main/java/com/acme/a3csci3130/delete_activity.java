package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;
/**
 * delete contact activity
 * the contact information can be deleted by clicking the list
 * after clicking the program will stay at the delete page
 */
public class delete_activity extends Activity {

    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    private MyApplicationData appState;


    protected void onCreate(Bundle savedInstanceState) {
        appState = ((MyApplicationData) getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_activity);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReferenceFromUrl("https://a4-84d55.firebaseio.com/");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.list3);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Contact>(this, Contact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Contact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);


        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact person = (Contact) firebaseAdapter.getItem(position);
                appState.firebaseReference.child(person.uid).removeValue();

            }
        });
    }


}
