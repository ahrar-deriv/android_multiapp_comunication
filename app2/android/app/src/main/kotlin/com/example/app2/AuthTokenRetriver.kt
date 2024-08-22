package com.example.app2

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle

object AuthTokenRetriever {
    private val AUTH_PROVIDER_URI: Uri = Uri.parse("content://com.example.app1.authprovider/auth")

    fun getAuthToken(context: Context): String? {
        val contentResolver: ContentResolver = context.contentResolver
        val result: Bundle? = contentResolver.call(AUTH_PROVIDER_URI, "getAuthToken", null, null)
        return result?.getString("auth_token")
    }
}
