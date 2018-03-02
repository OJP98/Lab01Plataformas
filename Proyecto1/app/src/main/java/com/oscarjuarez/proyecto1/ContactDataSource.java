package com.oscarjuarez.proyecto1;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Oscar Juarez on 28/02/2018.
 */

public class ContactDataSource implements IContactDataSource {

    private ContactDao contactDao;
    private static ContactDataSource mInstance;

    public ContactDataSource(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public static ContactDataSource getInstance(ContactDao contactDao) {

        if (mInstance == null)  {

            mInstance = new ContactDataSource(contactDao);

        }

        return mInstance;
    }

    @Override
    public Flowable<List<Contacto>> getAll() {
        return contactDao.getAll();
    }

    @Override
    public Flowable<Contacto> getUserById(int contactId) {
        return contactDao.getUserById(contactId);
    }

    @Override
    public Contacto findByName(String first, String last) {
        return contactDao.findByName(first, last);
    }

    @Override
    public void deleteAllContacts() {
        contactDao.deleteAllContacts();
    }

    @Override
    public void insertContact(Contacto... contacto) {
        contactDao.insertContact(contacto);
    }

    @Override
    public void updateContact(Contacto... contacto) {
        contactDao.updateContact(contacto);
    }

    @Override
    public void deleteContact(Contacto contacto) {

        contactDao.deleteContact(contacto);
    }
}
