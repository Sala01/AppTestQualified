package com.example.myapplication.data.model

data class FormResponse(
    val ok: Int,
    val data: Map<String, FieldDto>
)