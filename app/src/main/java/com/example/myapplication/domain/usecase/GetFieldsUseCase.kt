package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Field
import com.example.myapplication.domain.repository.FormRepository

open class GetFieldsUseCase(private val repository: FormRepository) {
    suspend operator fun invoke(): List<Field> = repository.getFields()
}