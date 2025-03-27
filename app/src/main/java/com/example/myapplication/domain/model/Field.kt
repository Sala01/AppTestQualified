package com.example.myapplication.domain.model

data class Field(
    val key: String,
    val label: String,
    val required: Boolean,
    val visible: Boolean,
    val order: Int,
    val maxLength: Int?,
    val regex: String?,
    val type: String,
    val values: List<String>? = null
)