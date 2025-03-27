package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Field
import com.example.myapplication.domain.usecase.GetFieldsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel(private val getFieldsUseCase: GetFieldsUseCase): ViewModel() {
    private val _fields = MutableStateFlow<List<Field>>(emptyList())
    val fields: StateFlow<List<Field>> = _fields

    init {
        viewModelScope.launch {
            _fields.value = getFieldsUseCase()
        }
    }
}
