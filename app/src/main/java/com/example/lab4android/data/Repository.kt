package com.example.lab4android.data

import android.content.Context
import com.example.lab4android.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.buffer
import okio.source

class Repository(private val context: Context)
{
    fun loadData(): List<Recommendation>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val adapter = moshi.adapter<List<Recommendation>>(Types.newParameterizedType(List::class.java, Recommendation::class.java))

        return context.resources.openRawResource(R.raw.recs).source().buffer().use { source ->
            adapter.fromJson(source)
        }
    }
}