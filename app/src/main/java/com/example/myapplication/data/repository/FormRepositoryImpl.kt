package com.example.myapplication.data.repository

import android.content.Context
import com.example.myapplication.data.model.FormResponse
import com.example.myapplication.domain.model.Field
import com.example.myapplication.domain.repository.FormRepository
import com.example.myapplication.data.model.toDomain
import com.google.gson.Gson


class FormRepositoryImpl(private val context: Context): FormRepository {
    override suspend fun getFields(): List<Field> {
        val json = context.assets.open("samples/sample.json").bufferedReader().use { it.readText() }
        val parsed = Gson().fromJson(json, FormResponse::class.java)
        return parsed.data
            .filter { it.value.visible }
            .toList()
            .sortedBy { it.second.order }
            .map { it.toDomain() }
    }
}