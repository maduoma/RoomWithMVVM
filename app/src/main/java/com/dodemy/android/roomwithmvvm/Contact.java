package com.dodemy.android.roomwithmvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
