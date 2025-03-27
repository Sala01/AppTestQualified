package com.example.myapplication.data.model

import com.example.myapplication.domain.model.Field

fun Pair<String, FieldDto>.toDomain(): Field {
    val key = this.first
    val dto = this.second
    val values = when (val raw = dto.values) {
        is Map<*, *> -> raw.values.mapNotNull { it?.toString() }
        is List<*> -> raw.mapNotNull { it?.toString() }
        else -> null
    }
    return Field(
        key = key,
        label = key.replace("-", " ").replaceFirstChar { it.uppercase() },
        required = dto.req,
        visible = dto.visible,
        order = dto.order,
        maxLength = dto.maxlength,
        regex = dto.regex,
        type = dto.type,
        values = values
    )
}
