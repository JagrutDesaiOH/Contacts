package com.example.jagrutdesai.contacts.views.contacts;

import com.example.jagrutdesai.contacts.data.contacts.Contact;

/**
 * Interface for the listener to handle deletion and edition of the contact.
 * Created by jagrut.desai on 5/12/18.
 */

public interface ContactListListener {

    void onClickContactDelete(Contact contact);
    void onClickContactEdit(Contact contact);

}
