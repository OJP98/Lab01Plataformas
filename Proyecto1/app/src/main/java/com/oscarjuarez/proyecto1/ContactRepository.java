package com.oscarjuarez.proyecto1;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Oscar Juarez on 28/02/2018.
 */

public class ContactRepository implements IContactDataSource {

    private IContactDataSource mLocalDataSource;

    private static ContactRepository mInstance;

    public ContactRepository(IContactDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static ContactRepository getInstance(IContactDataSource mLocalDataSource){

        if (mInstance == null) {
            mInstance = new ContactRepository(mLocalDataSource);
        }

        return mInstance;

    }

    @Override
    public Flowable<List<Contacto>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public Flowable<Contacto> getUserById(int contactId) {
        return mLocalDataSource.getUserById(contactId);
    }

    @Override
    public Contacto findByName(String first, String last) {
        return mLocalDataSource.findByName(first, last);
    }

    @Override
    public void deleteAllContacts() {

        mLocalDataSource.deleteAllContacts();

    }

    @Override
    public void insertContact(Contacto... contacto) {

        mLocalDataSource.insertContact(contacto);
        System.out.println("INGRESE EL CONTACTOOO WUJUUU");
        System.out.println(contacto);

    }

    @Override
    public void updateContact(Contacto... contacto) {

        mLocalDataSource.updateContact(contacto);
    }

    @Override
    public void deleteContact(Contacto contacto) {

        mLocalDataSource.deleteContact(contacto);

    }
}
