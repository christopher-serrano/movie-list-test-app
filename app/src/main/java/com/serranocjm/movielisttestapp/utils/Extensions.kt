package com.serranocjm.movielisttestapp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

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
