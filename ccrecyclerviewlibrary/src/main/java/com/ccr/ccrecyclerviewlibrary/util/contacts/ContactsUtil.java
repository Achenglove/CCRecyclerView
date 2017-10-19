package com.ccr.ccrecyclerviewlibrary.util.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;


import com.ccr.ccrecyclerviewlibrary.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewhale on 2016/2/23.
 */
public class ContactsUtil {
    /**
     * 获取手机联系人
     *
     * @param activity
     * @return
     */
    public static List<Contact> getPhoneContacts(Activity activity) {
        List<Contact> contacts = new ArrayList<>();
        ContentResolver resolver = activity.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
                new String[]{Phone.CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                //获取手机号码
                String number = phoneCursor.getString(2);
                //获取用户名
                String username = phoneCursor.getString(1);
                if (!StringUtil.isEmpty(number) && !StringUtil.isEmpty(username)) {
                    contacts.add(new Contact(username, number));
                }
            }
            phoneCursor.close();
        }
        return contacts;
    }


}
