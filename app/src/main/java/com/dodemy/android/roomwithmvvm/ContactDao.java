package com.dodemy.android.roomwithmvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
    public interface ContactDao {

        @Insert
        void insert(Contact contact);

        @Delete
        void delete(Contact contact);

        //We will call to show on RecyclerView
        @Query("SELECT * FROM contact_table ORDER BY name ASC")
        LiveData<List<Contact>> getAllContact();
}
