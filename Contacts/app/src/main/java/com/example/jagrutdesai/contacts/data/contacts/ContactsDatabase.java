package com.example.jagrutdesai.contacts.data.contacts;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * This class is singleton for accessing contact database.
 * we have named our database Contact.db for simplicity purposes.
 * Created by jagrut.desai on 5/11/18.
 */

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static volatile ContactsDatabase INSTANCE;

    public static ContactsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsDatabase.class, "Contacts.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}