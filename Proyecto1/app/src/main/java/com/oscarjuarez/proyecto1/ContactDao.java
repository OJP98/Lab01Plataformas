package com.oscarjuarez.proyecto1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Oscar Juarez on 28/02/2018.
 */

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contactos")
    Flowable<List<Contacto>> getAll();

    @Query("SELECT * FROM contactos WHERE id=:contactId")
    Flowable<Contacto> getUserById(int contactId);

    @Query("SELECT * FROM contactos WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    Contacto findByName(String first, String last);

    @Query("DELETE FROM contactos")
    void deleteAllContacts();

    @Insert
    void insertContact(Contacto... contacto);

    @Update
    void updateContact(Contacto... contacto);

    @Delete
    void deleteContact(Contacto contacto);

}
