package com.todo.todoappcompose.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.todo.todoappcompose.R

/**
 * Shared preferences
 */
object SharedPrefs {
    private var prefs: SharedPreferences? = null

    /**
     * Initialise shared preferences without encryption
     * @param context
     */
    private fun initDefaultPrefs(context: Context) {
        prefs = context.getSharedPreferences(context.getString(R.string.TODOApp), MODE_PRIVATE)
    }

    fun getStr(context: Context, key: String): String? {
        initDefaultPrefs(context)

        return try {
            prefs!!.getString(key, "")
        } catch (ex: ClassCastException) {
            ""
        }
    }

    }