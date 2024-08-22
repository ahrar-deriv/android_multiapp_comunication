package com.example.app1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

public class AuthTokenProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.app1.authprovider";
    private static final String BASE_PATH = "auth";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    Map<String, Object> options = new HashMap<String, Object>() {{
        put("encryptedSharedPreferences", true);
        put("resetOnError", false);
        put("keyCipherAlgorithm", KeyCipherAlgorithm.RSA_ECB_PKCS1Padding);
        put("storageCipherAlgorithm", StorageCipherAlgorithm.AES_CBC_PKCS7Padding);
    }};

    enum KeyCipherAlgorithm {
        RSA_ECB_PKCS1Padding,
        RSA_ECB_OAEPwithSHA_256andMGF1Padding,
    }

    enum StorageCipherAlgorithm {
        AES_CBC_PKCS7Padding,
        AES_GCM_NoPadding,
    }

    static {
        /*
         * The calls to addURI() go here for all the content URI patterns that the provider
         * recognizes. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to one. No wildcard is used
         * in the path.
         */
        uriMatcher.addURI(AUTHORITY, "auth", 1);

    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onCreate() {
        Log.println(Log.ERROR, "Provider Mine", "on create content provider local call");
        try {
            Context context = getContext();

        } catch (
                Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        if (method.equals("getAuthToken")) {
            Bundle bundle = new Bundle();
//            String authToken = sharedPreferences.getString("auth_token", null);
//            new Thread(() -> {

            Map<String, String> authToken = null;
            try {
//                    authToken = secureStorage.readAll();
//                    authToken = sharedPreferences.get();
                FlutterSecureStorage flutterSecureStorage = new FlutterSecureStorage(getContext(), options);
                String tok = flutterSecureStorage.read(flutterSecureStorage.ELEMENT_PREFERENCES_KEY_PREFIX + "_auth_token");
                Log.println(Log.WARN, "app1 tok", tok);
            } catch (
                    Exception e) {
                throw new RuntimeException(e);
            }

//            }).run();
           /* try {

            bundle.putString("auth_token",tok );
            } catch (
                    Exception e) {
                throw new RuntimeException(e);
            }*/
            return bundle;
        }
        return super.call(method, arg, extras);
    }
}
