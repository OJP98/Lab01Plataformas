package com.oscarjuarez.proyecto1;

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

public interface IContactDataSource {

    Flowable<List<Contacto>> getAll();
    Flowable<Contacto> getUserById(int contactId);
    Contacto findByName(String first, String last);
    void deleteAllContacts();
    void insertContact(Contacto... contacto);
    void updateContact(Contacto... contacto);
    void deleteContact(Contacto contacto);

}
