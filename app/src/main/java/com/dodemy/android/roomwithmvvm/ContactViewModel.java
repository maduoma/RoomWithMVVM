package com.dodemy.android.roomwithmvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mContactRepository;
    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepository(application);
        mAllContacts = mContactRepository.getAllContacts();
    }

    public void insert(Contact contact){
        mContactRepository.insert(contact);


    }
    public void delete(Contact contact){
       mContactRepository.delete(contact);

    }

    public LiveData<List<Contact>> getAllContacts(){
        return mAllContacts;

    }

}
