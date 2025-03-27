package com.example.myapplication.presentation.viewmodel

import com.example.myapplication.domain.model.Field
import com.example.myapplication.domain.repository.FormRepository
import com.example.myapplication.domain.usecase.GetFieldsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeUseCase : GetFieldsUseCase(
    repository = object : FormRepository {
        override suspend fun getFields(): List<Field> {
            return listOf(
                Field("email", "Email", true, true, 1, 50, ".+@.+\\..+".toRegex().pattern, "text")
            )
        }
    }
)

class FormViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: FormViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FormViewModel(FakeUseCase())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fields should be populated after init`(): Unit = runTest {
        val result = viewModel.fields.first()
        assertEquals(1, result.size)
        assertEquals("email", result[0].key)
    }
}