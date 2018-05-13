package com.example.jagrutdesai.contacts.data.contacts;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * This class will act as our data access object layer which will talk to room and
 * provide us with Query/Insert/Delete/Update functionality for contact table.
 * Created by jagrut.desai on 5/11/18.
 */

@Dao
public interface ContactDao {

    /**
     * @return all the contacts in database
     */
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContacts();

    /**
     * insert a contact in database
     * @param contacts
     */
    @Insert(onConflict = REPLACE)
    void insertContact(Contact... contacts);

    /**
     * Updates a existed contact in database
     * @param contact
     */
    @Update
    void updateContact(Contact contact);

    /**
     * Deletes a existed contact in database
     * @param contact
     */
    @Delete
    void deleteContact(Contact contact);

}