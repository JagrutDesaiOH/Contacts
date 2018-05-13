package com.example.jagrutdesai.contacts.views.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jagrutdesai.contacts.R;
import com.example.jagrutdesai.contacts.data.contacts.Contact;
import com.example.jagrutdesai.contacts.viewmodels.contacts.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity is for showcasing the list of all the contact.
 * Also user can go to add/edit contact screen from here.
 * Created by jagrut.desai on 5/11/18.
 */

public class ContactsActivity extends AppCompatActivity implements ContactListListener{

    private ContactsViewModel contactsViewModel;
    private ContactListAdapter contactListAdapter;
    private AppCompatButton addContactButton;

    /**
     * Setup View
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        RecyclerView recyclerView = findViewById(R.id.contact_list);
        contactListAdapter = new ContactListAdapter(new ArrayList<Contact>(), this);
        setUpViewModel();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        addContactButton = findViewById(R.id.add_contancts_button);
        setUpAddContactButton();
    }

    /**
     * Setup the listener for the add contact button.
     */
    private void setUpAddContactButton() {
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddEditContact(null);
            }
        });
    }

    /**
     * Setup view model and observer for contact list,
     */
    private void setUpViewModel() {
        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        contactsViewModel.getContacts().observe(ContactsActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                contactListAdapter.onItemChanges(contacts);
            }
        });
    }

    /**
     * listener for the contact deletion
     * this communicates with view model.
     * @param contact
     */
    @Override
    public void onClickContactDelete(Contact contact) {
        contactsViewModel.deleteContact(contact);
    }

    /**
     * listener for the contact add/edit screen.
     * this communicates with view model.
     * @param contact
     */
    @Override
    public void onClickContactEdit(Contact contact) {
        goToAddEditContact(contact);
    }

    /**
     * Start add and edit activity with a contact.
     * Note contact could be null when you are trying to create new contact.
     * @param contact
     */
    private void goToAddEditContact(Contact contact) {
        Intent intent = new Intent(this, AddEditContactActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
