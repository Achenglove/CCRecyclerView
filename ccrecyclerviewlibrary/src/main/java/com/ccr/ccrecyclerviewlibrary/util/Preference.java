package com.ccr.ccrecyclerviewlibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Set;

public class Preference {

    // share数据保存

    private final String SHARED_PREFERENCE_NAME = "Acheng";
    private static Preference catche;
    private SharedPreferences spf;

    public static Preference instance(Context context) {
        if (catche == null) {
            catche = new Preference(context);
        }
        return catche;
    }

    public static Preference getInstance() {
        return catche;
    }

    public Preference(Context context) {
        spf = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        spf.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return spf.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean value) {
        return spf.getBoolean(key, value);
    }

    public void putString(String key, String value) {
        spf.edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return spf.getString(key, "");
    }

    public void putInt(String key, int value) {
        spf.edit().putInt(key, value).commit();
    }

    public void putLong(String key, long value) {
        spf.edit().putLong(key, value).commit();
    }

    public int getInt(String key) {
        return spf.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return spf.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return spf.getLong(key, 0);
    }

    public long getLong(String key, long def) {
        return spf.getLong(key, def);
    }

    public void putHashSet(String key, HashSet hashSet) {
        spf.edit().putStringSet(key, hashSet).commit();
    }

    public Set<String> getHashSet(String key) {
        return spf.getStringSet(key, new HashSet<String>());
    }

    public void clearData() {
        spf.edit().clear().commit();
    }

    public void remove(String key) {
        spf.edit().remove(key).commit();
    }

    public void commit() {
        spf.edit().commit();
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (StringUtil.isEmpty(getString(key))) {
            return null;
        }
        return JsonUtil.fromJson(getString(key), clazz);
    }

    public <T> T getObject(String key, TypeToken<T> token) {
        if (StringUtil.isEmpty(getString(key))) {
            return null;
        }
        return JsonUtil.fromJson(getString(key), token);
    }


    public void putObject(String key, Object object) {
        if (null == object) {
            putString(key, "");
        } else {
            String str = JsonUtil.toJson(object);
            putString(key, str);
        }
    }


}
