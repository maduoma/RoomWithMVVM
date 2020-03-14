package com.dodemy.android.roomwithmvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    private static Context activity;
    private static ContactDatabase mInstance;
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(mInstance).execute();
        }
    };

    public static synchronized ContactDatabase getInstance(Context context) {

        activity = context.getApplicationContext();

        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contact_database")
                    .fallbackToDestructiveMigrationFrom()
                    .addCallback(roomCallback)
                    .build();
        }
        return mInstance;
    }

    private static void fillWithStartingData(Context context) {
        ContactDao dao = getInstance(context).mContactDao();

        JSONArray contacts = loadJSONArray(context);

        try {
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject contact = contacts.getJSONObject(i);
                String contactName = contact.getString("name");
                String phoneNumber = contact.getString("phone");
                dao.insert(new Contact(contactName, phoneNumber));


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray loadJSONArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.contact_list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("contacts");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract ContactDao mContactDao();

    public static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao mContactDao;

        private PopulateAsyncTask(ContactDatabase db) {
            mContactDao = db.mContactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mContactDao.insert(new Contact("Ethan Maduabughichi", "1234567890"));
            mContactDao.insert(new Contact("Brian Maduabughichi", "0123456789"));
            fillWithStartingData(activity);
            return null;
        }
    }


}
