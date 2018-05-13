package com.example.jagrutdesai.contacts.views.contacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jagrutdesai.contacts.R;
import com.example.jagrutdesai.contacts.data.contacts.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * This is adapter for showcasing all the contac user have.
 * Created by jagrut.desai on 5/12/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter {

    private List<Contact> contacts = new ArrayList<>();
    private ContactListListener listListener;

    public ContactListAdapter (ArrayList<Contact> contacts, ContactListListener listListener){
        this.contacts = contacts;
        this.listListener = listListener;
    }

    /**
     * Inflates the item iew and create view holder for each contact item.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.view_contact_item, parent,false);
        return new ContactViewHolder(itemView);
    }

    /**
     * Binds the view to data and setup button click listeners.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);
        ((ContactViewHolder)holder).name.setText(contact.getName());
        ((ContactViewHolder)holder).phone.setText(contact.getPhoneNumber());
        ((ContactViewHolder)holder).address.setText(contact.getAddress());
        ((ContactViewHolder)holder).email.setText(contact.getEmail());
        (((ContactViewHolder)holder).deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listListener.onClickContactDelete(contacts.get(position));
            }
        });
        ((ContactViewHolder)holder).editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listListener.onClickContactEdit(contacts.get(position));
            }
        });
    }

    /**
     * Size of the contact list.
     * @return
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    /**
     * For dataset changes and notifying adapter that dataset changed.
     * @param contacts
     */
    public void onItemChanges(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    /**
     * View Holder for the contact item view.
     */
    public class ContactViewHolder  extends RecyclerView.ViewHolder {
        AppCompatTextView name, phone, address, email;
        AppCompatImageButton deleteButton, editButton;
        public ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone_number);
            address = itemView.findViewById(R.id.contact_address);
            email = itemView.findViewById(R.id.contact_email);
            deleteButton = itemView.findViewById(R.id.contact_delete);
            editButton = itemView.findViewById(R.id.contact_edit);
        }
    }
}
