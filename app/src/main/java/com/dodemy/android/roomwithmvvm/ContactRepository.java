package com.dodemy.android.roomwithmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    public ContactRepository(Application application){
        ContactDatabase database = ContactDatabase.getInstance(application);
        mContactDao = database.mContactDao();
        mAllContacts = mContactDao.getAllContact();
    }
    public void insert(Contact contact){
        new InsertContactAsyncTask(mContactDao).execute(contact);

    }
    public void delete(Contact contact){
        new DeleteContactAsyncTask(mContactDao).execute(contact);

    }

    public LiveData<List<Contact>> getAllContacts(){
        return mAllContacts;
    }

    public static class InsertContactAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDao contactDao;
        private InsertContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;

        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }


    public static class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDao contactDao;
        private DeleteContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;

        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }
}
