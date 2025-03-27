package com.example.myapplication.data.model

data class FieldDto(
    val req: Boolean,
    val visible: Boolean,
    val order: Int,
    val maxlength: Int?,
    val type: String,
    val regex: String?,
    val values: Any?
)