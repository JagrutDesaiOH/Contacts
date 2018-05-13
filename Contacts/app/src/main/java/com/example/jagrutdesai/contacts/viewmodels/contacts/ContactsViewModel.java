package com.example.jagrutdesai.contacts.viewmodels.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.jagrutdesai.contacts.data.contacts.Contact;
import com.example.jagrutdesai.contacts.data.contacts.ContactRepository;

import java.util.List;

/**
 * This class is the viewmodel for the viewing contact list
 * It will use contact repository get all the contact and delete them from database.
 * Created by jagrut.desai on 5/12/18.
 */

public class ContactsViewModel extends AndroidViewModel {

    private final LiveData<List<Contact>> contacts;
    private ContactRepository contactRepository;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        contacts = contactRepository.getAllContacts();
    }

    /**
     * Get all contacts from databse.
     * @return
     */
    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    /**
     * Delete contact.
     * @param contact
     */
    public void deleteContact(Contact contact){
        contactRepository.deleteContact(contact);
    }
}
