package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Field

interface FormRepository {
    suspend fun getFields(): List<Field>
}