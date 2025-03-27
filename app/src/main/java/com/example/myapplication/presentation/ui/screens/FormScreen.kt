package com.example.myapplication.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Field

@Composable
fun FormScreen(fields: List<Field>, onValidate: (Boolean) -> Unit) {
    val inputValues = remember { mutableStateMapOf<String, String>() }
    val fieldErrors = remember { mutableStateMapOf<String, String?>() }

    Column(modifier = Modifier.padding(16.dp)) {
        fields.forEach { field ->
            val value = inputValues[field.key] ?: ""
            OutlinedTextField(
                value = value,
                onValueChange = {
                    inputValues[field.key] = it
                    fieldErrors[field.key] = validateField(field, it)
                },
                label = { Text(field.label) },
                isError = fieldErrors[field.key] != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            fieldErrors[field.key]?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }

        Button(
            onClick = {
                val valid = fields.all {
                    val value = inputValues[it.key] ?: ""
                    validateField(it, value) == null
                }
                onValidate(valid)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Validar")
        }
    }
}

fun validateField(field: Field, value: String): String? {
    if (field.maxLength != null && value.length > field.maxLength)
        return "Máximo ${field.maxLength} caracteres"
    if (!field.regex.isNullOrBlank() && !Regex(field.regex).matches(value))
        return "Formato inválido"
    return null
}