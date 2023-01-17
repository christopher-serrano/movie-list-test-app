package com.serranocjm.movielisttestapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.serranocjm.movielisttestapp.utils.custom.OnOneOffClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.Charset

// utility to return type tokens
inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}

inline fun <reified T> parseArray(json: String, typeToken: Type): T {
    val gson = GsonBuilder().create()
    return gson.fromJson<T>(json, typeToken)
}

inline fun <reified T : Any?> Any?.parseAny(typeToken: Type): T? {
    return parseArray(Gson().toJson(this), typeToken)
}

inline fun <reified T : Any> Any?.mapTo(newClass: Class<T>): T? =
    Gson().run {
        fromJson(toJson(this@mapTo), newClass)
    }

/**
 * Parser extensions
 */
fun Any?.toJsonString(): String? {
    return this?.let {
        val gson = Gson()
        gson.toJson(it)
    }
}

fun <T> String.objectFromJson(classOfT: Class<T>): Any? {
    val gson = Gson()
    return gson.fromJson<Any>(this, classOfT as Class<*>)
}

inline fun <reified T : Any> String.toKotlinObject(): T {
    val gson = Gson()
    return gson.fromJson(this, T::class.java)
}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}

fun delayAction(delay: Long, action: () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
    kotlinx.coroutines.delay(delay)
    action.invoke()
}

fun View.setOneOffClickListener(action: () -> Unit) {
    setOnClickListener(object : OnOneOffClickListener() {
        override fun onSingleClick(v: View?) {
            action.invoke()
        }
    })
}

fun Context.toastLong(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.toastShort(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Int.parseDuration(): String {
    val hours: Int = this / 60
    val minutes: Int = this % 60
    return "${hours}h ${minutes}m"
}
