package com.ccr.ccrecyclerviewlibrary.util.contacts;


import com.ccr.ccrecyclerviewlibrary.util.HanziToPinyin;
import com.ccr.ccrecyclerviewlibrary.util.StringUtil;

/**
 * Created by ewhale on 2016/2/23.
 */
public class Contact {
    String userName;
    String phoneNumber;
    char sortKey;
    boolean sign;

    public Contact(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber.trim();
        if (StringUtil.isEmpty(userName) || Character.isDigit(userName.charAt(0))) {
            sortKey = '#';
        } else {
            sortKey = HanziToPinyin.getInstance().get(userName.substring(0, 1))
                    .get(0).target.substring(0, 1).toUpperCase().charAt(0);
            if (sortKey < 'A' || sortKey > 'Z') {
                sortKey = '#';
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char getSortKey() {
        return sortKey;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
