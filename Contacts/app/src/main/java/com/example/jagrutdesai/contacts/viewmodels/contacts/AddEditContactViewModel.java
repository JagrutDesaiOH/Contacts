package com.example.jagrutdesai.contacts.viewmodels.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.jagrutdesai.contacts.data.contacts.Contact;
import com.example.jagrutdesai.contacts.data.contacts.ContactRepository;

/**
 * This class is the viewmodel for the add and edit contact screen.
 * this class will communicate through contact repository.
 * Created by jagrut.desai on 5/12/18.
 */

public class AddEditContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public AddEditContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
    }

    /**
     * Update contact.
     * @param contact
     */
    public void updateContact(Contact contact){
        contactRepository.updateContact(contact);
    }

    /**
     * Insert contact.
     * @param contact
     */
    public void insertContact(Contact contact){
        contactRepository.insertContact(contact);
    }
}
