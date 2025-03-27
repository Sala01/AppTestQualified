package com.example.myapplication.utils

import com.example.myapplication.domain.model.Field
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorTest {

    @Test
    fun `field should fail if longer than maxlength`() {
        val field = Field("key", "Label", true, true, 1, 5, null, "text")
        val result = validate(field, "toolonginput")
        assertEquals("Máximo 5 caracteres", result)
    }

    @Test
    fun `field should fail if regex does not match`() {
        val field = Field("phone", "Phone", true, true, 1, 10, "^\\d{3}$", "text")
        val result = validate(field, "abc")
        assertEquals("Formato inválido", result)
    }

    @Test
    fun `field should pass if valid`() {
        val field = Field("name", "Name", true, true, 1, 10, "^[a-zA-Z]+$", "text")
        val result = validate(field, "Carlos")
        assertEquals(null, result)
    }

    private fun validate(field: Field, value: String): String? {
        if (field.maxLength != null && value.length > field.maxLength!!)
            return "Máximo ${field.maxLength} caracteres"
        if (!field.regex.isNullOrBlank() && !Regex(field.regex!!).matches(value))
            return "Formato inválido"
        return null
    }
}