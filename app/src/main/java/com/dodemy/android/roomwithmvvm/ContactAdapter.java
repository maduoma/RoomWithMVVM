package com.dodemy.android.roomwithmvvm;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
private List<Contact> mContacts = new ArrayList<>();


    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder contactHolder, int position) {
        Contact currContact = mContacts.get(position);
        contactHolder.mName.setText(currContact.getName());
        contactHolder.mNumber.setText(currContact.getNumber());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void setContacts(List<Contact> contacts){
        this.mContacts = contacts;
        notifyDataSetChanged();
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mNumber;

        public ContactHolder(@Nullable View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.contactName);
            mNumber = itemView.findViewById(R.id.contactNumber);
        }
    }
}