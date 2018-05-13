package com.example.jagrutdesai.contacts.data.contacts;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * This interface will be structure on how we interact with data both
 * remote source(network) and local source(database).
 * Created by jagrut.desai on 5/12/18.
 */

public interface ContactDataSource {

    LiveData<List<Contact>> getAllContacts();

    void insertContact(Contact... contacts);

    void updateContact(Contact contact);

    void deleteContact(Contact contact);

}
