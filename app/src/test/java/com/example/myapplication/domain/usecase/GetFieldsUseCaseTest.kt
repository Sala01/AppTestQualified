package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Field
import com.example.myapplication.domain.repository.FormRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeFormRepository : FormRepository {
    override suspend fun getFields(): List<Field> {
        return listOf(
            Field("firstname", "First Name", true, true, 2, 50, null, "text"),
            Field("lastname", "Last Name", true, true, 1, 50, null, "text"),
            Field("iban", "IBAN", false, false, 3, 31, null, "text") // No visible
        )
    }
}

class GetFieldsUseCaseTest {

    private lateinit var useCase: GetFieldsUseCase

    @Before
    fun setup() {
        useCase = GetFieldsUseCase(FakeFormRepository())
    }

    @Test
    fun `getFields should return only visible fields ordered by order`(): Unit = runTest {
        val result = useCase()

        assertEquals(3, result.size)
        assertEquals("firstname", result[0].key)
        assertEquals("lastname", result[1].key)
    }
}