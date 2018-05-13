package com.example.jagrutdesai.contacts.data.contacts;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

/**
 * This is a central class where application comes to get all things related to contacts.
 * This class will be responsible for getting data from both remote and local sources.
 * It could also be used to handle any type of syncronization between local and remote sources.
 * For our purposes we do not have remote data sources.
 * Created by jagrut.desai on 5/12/18.
 */

public class ContactRepository implements ContactDataSource {

    private LocalContactDataSource localContactDataSource;

    public ContactRepository (Context context){
        this.localContactDataSource = new LocalContactDataSource(context);
    }

    /**
     * Get all the contact from local data source.
     * @return
     */
    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return localContactDataSource.getAllContacts();
    }

    /**
     * insert contact in database.
     * @param contacts
     */
    @Override
    public void insertContact(Contact... contacts) {
        localContactDataSource.insertContact(contacts);
    }

    /**
     * update contact in database.
     * @param contact
     */
    @Override
    public void updateContact(Contact contact) {
        localContactDataSource.insertContact(contact);
    }

    /**
     * delete contact in database.
     * @param contact
     */
    @Override
    public void deleteContact(Contact contact) {
        localContactDataSource.deleteContact(contact);
    }

}
