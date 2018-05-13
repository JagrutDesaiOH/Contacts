package com.example.jagrutdesai.contacts.views.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.jagrutdesai.contacts.R;
import com.example.jagrutdesai.contacts.data.contacts.Contact;
import com.example.jagrutdesai.contacts.viewmodels.contacts.AddEditContactViewModel;

/**
 * This activity is for add and edit contacts.
 */
public class AddEditContactActivity extends AppCompatActivity {

    private AppCompatButton saveContactButton;
    private AddEditContactViewModel addEditContactViewModel;
    private boolean newContact = true;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);
        saveContactButton = findViewById(R.id.save_contact_button);
        contact = (Contact) getIntent().getExtras().getSerializable("contact");
        if (contact != null) {
            newContact = false;
        }
        addEditContactViewModel = ViewModelProviders.of(this).get(AddEditContactViewModel.class);
        setUpContact();
        setUpSaveContactButton();
    }

    /**
     * In case of user editing existing contact we need to pre fill
     * the contact information that they are trying to edit.
     */
    private void setUpContact() {
        if (contact != null) {
            TextInputEditText firstname = findViewById(R.id.contact_first_name);
            TextInputEditText lastname = findViewById(R.id.contact_last_name);
            TextInputEditText phoneNumber = findViewById(R.id.contact_phone_number);
            TextInputEditText address = findViewById(R.id.contact_address);
            TextInputEditText email = findViewById(R.id.contact_email);

            firstname.setText(contact.getFirstName());
            lastname.setText(contact.getLastName());
            phoneNumber.setText(contact.getPhoneNumber());
            address.setText(contact.getAddress());
            email.setText(contact.getEmail());
        }
    }

    /**
     * Setup the button click listener to save the contact.
     * finish the activity when done.
     */
    private void setUpSaveContactButton() {
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = ((TextInputEditText) findViewById(R.id.contact_first_name)).getText().toString();
                String lastname = ((TextInputEditText) findViewById(R.id.contact_last_name)).getText().toString();
                String phoneNumber = ((TextInputEditText) findViewById(R.id.contact_phone_number)).getText().toString();
                String address = ((TextInputEditText) findViewById(R.id.contact_address)).getText().toString();
                String email = ((TextInputEditText) findViewById(R.id.contact_email)).getText().toString();
                if (newContact) {
                    contact = new Contact(firstname, lastname, phoneNumber, address, email);
                    addEditContactViewModel.insertContact(contact);
                } else {
                    contact.setFirstName(firstname);
                    contact.setLastName(lastname);
                    contact.setPhoneNumber(phoneNumber);
                    contact.setAddress(address);
                    contact.setEmail(email);
                    addEditContactViewModel.updateContact(contact);
                }
                finish();
            }
        });
    }
}
