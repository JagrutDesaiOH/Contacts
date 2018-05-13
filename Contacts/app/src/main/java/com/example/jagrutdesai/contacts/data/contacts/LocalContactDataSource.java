package com.example.jagrutdesai.contacts.data.contacts;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * This is our local source for everything contact related.
 * this class will have implementation of how we interact with database.
 * Created by jagrut.desai on 5/12/18.
 */

public class LocalContactDataSource implements ContactDataSource {

    ContactsDatabase contactsDatabase;
    private final Executor databaseExecutor;

    public LocalContactDataSource(Context context){
        this.contactsDatabase = ContactsDatabase.getInstance(context);
        databaseExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * Get all the contact from database.
     * @return
     */
    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return contactsDatabase.contactDao().getAllContacts();
    }

    /**
     * insert contact into database on background thread.
     * @param contacts
     */
    @Override
    public void insertContact(final Contact... contacts) {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDatabase.contactDao().insertContact(contacts);
            }
        });
    }

    /**
     * update contact into database on background thread.
     * @param contact
     */
    @Override
    public void updateContact(final Contact contact) {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDatabase.contactDao().updateContact(contact);
            }
        });
    }

    /**
     * delete contact into database on background thread.
     * @param contact
     */
    @Override
    public void deleteContact(final Contact contact) {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDatabase.contactDao().deleteContact(contact);
            }
        });
    }

}
