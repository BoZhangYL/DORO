package ckt.android;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import java.util.ArrayList;
import ckt.base.VP4;

/**
 * Created by qiang.zhang on 2017/1/16.
 */
public class ContactsManager extends VP4{
    private static ContentResolver contentResolver;
    private static final String COLUMN_CONTACT_ID =
            ContactsContract.Data.CONTACT_ID;
    private static final String COLUMN_RAW_CONTACT_ID =
            ContactsContract.Data.RAW_CONTACT_ID;
    private static final String COLUMN_MIMETYPE =
            ContactsContract.Data.MIMETYPE;
    private static final String COLUMN_NAME =
            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;
    private static final String COLUMN_NUMBER =
            ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static final String COLUMN_NUMBER_TYPE =
            ContactsContract.CommonDataKinds.Phone.TYPE;
    private static final String COLUMN_EMAIL =
            ContactsContract.CommonDataKinds.Email.DATA;
    private static final String COLUMN_EMAIL_TYPE =
            ContactsContract.CommonDataKinds.Email.TYPE;
    private static final String MIMETYPE_STRING_NAME =
            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;
    private static final String MIMETYPE_STRING_PHONE =
            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
    private static final String MIMETYPE_STRING_EMAIL =
            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;

    public ContactsManager() {
        initDevice();
        this.contentResolver = instrument.getTargetContext().getContentResolver();
    }
    /**
     * Search and fill the contact information by the contact name given.
     * @param name Only the name is necessary.
     */
    public Contact searchContact(String name) {
        logger.info("**search start**");
        Contact contact = new Contact();
        contact.setName(name);
        logger.info("search name: " + contact.getName());
        String id = getContactID(contact.getName());
        contact.setId(id);
        if(id.equals("0")) {
            logger.info(contact.getName() + " not exist. exit.");
        } else {
            logger.info("find id: " + id);
            //Fetch Phone Number
            Cursor cursor = contentResolver.query(
                    android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{COLUMN_NUMBER, COLUMN_NUMBER_TYPE},
                    COLUMN_CONTACT_ID + "='" + id + "'", null, null);
            while(cursor.moveToNext()) {
                contact.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
                contact.setNumberType(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_TYPE)));
                logger.info("find number: " + contact.getNumber());
                logger.info("find numberType: " + contact.getNumberType());
            }
            //cursor.close();

            //Fetch email
            cursor = contentResolver.query(
                    android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{COLUMN_EMAIL, COLUMN_EMAIL_TYPE},
                    COLUMN_CONTACT_ID + "='" + id + "'", null, null);
            while(cursor.moveToNext()) {
                contact.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                contact.setEmailType(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL_TYPE)));
                logger.info("find email: " + contact.getEmail());
                logger.info("find emailType: " + contact.getEmailType());
            }
            cursor.close();
        }
        logger.info("**search end**");
        return contact;
    }

    /**
     *
     * @param name The contact who you get the id from. The name of
     * the contact should be set.
     * @return 0 if contact not exist in contacts list. Otherwise return
     * the id of the contact.
     */
    public static String getContactID(String name) {
        String id = "0";
        Cursor cursor = contentResolver.query(
                android.provider.ContactsContract.Contacts.CONTENT_URI,
                new String[]{android.provider.ContactsContract.Contacts._ID},
                android.provider.ContactsContract.Contacts.DISPLAY_NAME +
                        "='" + name + "'", null, null);
        if(cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex(
                    android.provider.ContactsContract.Contacts._ID));
        }
        return id;
    }
    public static String getContactName(String id) {
        String name="";
        ContentResolver contentResolver = instrument.getTargetContext().getContentResolver();
        Cursor cursor = contentResolver.query(
                android.provider.ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while(cursor.moveToNext()) {
            String queryId=cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
            if (id.equals(queryId)){
                name=cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));
            }
        }
        cursor.close();
        return name;
    }
    public ArrayList<String> getAllContactID() {
        ArrayList<String> ids = new ArrayList<>();
        Cursor cursor = contentResolver.query(
                android.provider.ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
            ids.add(id);
            logger.info("id is -"+id);
        }
        cursor.close();
        return ids;
    }

    /**
     * You must specify the contact's ID.
     * @param contact
     * @throws Exception The contact's name should not be empty.
     */
    public void addContact(Contact contact) {
        logger.info("**add start**");
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        String id = getContactID(contact.getName());
        if(!id.equals("0")) {
            logger.info("contact already exist. exit.");
        } else if(contact.getName().trim().equals("")){
            logger.info("contact name is empty. exit.");
        } else {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(COLUMN_RAW_CONTACT_ID, 0)
                    .withValue(COLUMN_MIMETYPE, MIMETYPE_STRING_NAME)
                    .withValue(COLUMN_NAME, contact.getName())
                    .build());
            logger.info("add name: " + contact.getName());

            if(!contact.getNumber().trim().equals("")) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(COLUMN_RAW_CONTACT_ID, 0)
                        .withValue(COLUMN_MIMETYPE, MIMETYPE_STRING_PHONE)
                        .withValue(COLUMN_NUMBER, contact.getNumber())
                        .withValue(COLUMN_NUMBER_TYPE, contact.getNumberType())
                        .build());
                logger.info("add number: " + contact.getNumber());
            }

            if(!contact.getEmail().trim().equals("")) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(COLUMN_RAW_CONTACT_ID, 0)
                        .withValue(COLUMN_MIMETYPE, MIMETYPE_STRING_EMAIL)
                        .withValue(COLUMN_EMAIL, contact.getEmail())
                        .withValue(COLUMN_EMAIL_TYPE, contact.getEmailType())
                        .build());
                logger.info("add email: " + contact.getEmail());
            }

            try {
                contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
                logger.info("add contact success.");
            } catch (Exception e) {
                logger.info("add contact failed.");
                logger.info(e.getMessage());
            }
        }
        logger.info("**add end**");

    }

    /**
     * Delete contacts who's name equals contact.getName();
     * @param contact
     */
    public void deleteContact(Contact contact) {
        logger.info("**delete start**");
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        String id = getContactID(contact.getName());
        //delete contact
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.RawContacts.CONTACT_ID+"="+id, null)
                .build());
        //delete contact information such as phone number,email
        ops.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI)
                .withSelection(COLUMN_CONTACT_ID + "=" + id, null)
                .build());
        logger.info("delete contact: " + contact.getName());

        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
            logger.info("delete contact success");
        } catch (Exception e) {
            logger.info("delete contact failed");
            logger.info(e.getMessage());
        }
        logger.info("**delete end**");
    }

    /**
     * @param contactOld The contact wants to be updated. The name should exists.
     * @param contactNew
     */
    public void updateContact(Contact contactOld, Contact contactNew) {
        logger.info("**update start**");
        String id = getContactID(contactOld.getName());
        if(id.equals("0")) {
            logger.info(contactOld.getName()+" not exist.");
        } else if(contactNew.getName().trim().equals("")){
            logger.info("contact name is empty. exit.");
        } else if(!getContactID(contactNew.getName()).equals("0")){
            logger.info("new contact name already exist. exit.");
        } else {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            //update name
            ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(COLUMN_CONTACT_ID + "=? AND " + COLUMN_MIMETYPE + "=?",
                            new String[]{id, MIMETYPE_STRING_NAME})
                    .withValue(COLUMN_NAME, contactNew.getName())
                    .build());
            logger.info("update name: " + contactNew.getName());

            //update number
            if(!contactNew.getNumber().trim().equals("")) {
                ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                        .withSelection(COLUMN_CONTACT_ID + "=? AND " + COLUMN_MIMETYPE + "=?",
                                new String[]{id, MIMETYPE_STRING_PHONE})
                        .withValue(COLUMN_NUMBER, contactNew.getNumber())
                        .withValue(COLUMN_NUMBER_TYPE, contactNew.getNumberType())
                        .build());
                logger.info("update number: " + contactNew.getNumber());
            }

            //update email if mail
            if(!contactNew.getEmail().trim().equals("")) {
                ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                        .withSelection(COLUMN_CONTACT_ID + "=? AND " + COLUMN_MIMETYPE + "=?",
                                new String[]{id, MIMETYPE_STRING_EMAIL})
                        .withValue(COLUMN_EMAIL, contactNew.getEmail())
                        .withValue(COLUMN_EMAIL_TYPE, contactNew.getEmailType())
                        .build());
                logger.info("update email: " + contactNew.getEmail());
            }

            try {
                contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
                logger.info("update success");
            } catch (Exception e) {
                logger.info("update failed");
                logger.info(e.getMessage());
            }
        }
        logger.info("**update end**");
    }
    public  void fetchAllContacts(){
        initDevice();
        ContentResolver contentResolver = instrument.getTargetContext().getContentResolver();
        Cursor cursor = contentResolver.query(
                android.provider.ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while(cursor.moveToNext()) {
            logger.info(cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID)));
            logger.info(cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME)));
        }
        logger.info("total is-"+cursor.getCount());
        cursor.close();
    }
    public void deleteAll(){
        logger.info("**delete start**");
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ArrayList<String> ids = getAllContactID();
        for (String id:ids) {
            String name=getContactName(id);
            if (name!=null){
                contentResolver.delete(uri, "display_name=?", new String[]{name});
                uri = Uri.parse("content://com.android.contacts/data");
                contentResolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
            }
        }
    }
}
