package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myapplication.data.repository.FormRepositoryImpl
import com.example.myapplication.domain.repository.FormRepository
import com.example.myapplication.domain.usecase.GetFieldsUseCase
import com.example.myapplication.presentation.ui.screens.FormScreen
import com.example.myapplication.presentation.viewmodel.FormViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.compose.getKoin
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appModule = module {
            single<FormRepository> { FormRepositoryImpl(androidContext()) }
            single { GetFieldsUseCase(get()) }
            viewModel { FormViewModel(get()) }
        }


        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            val viewModel: FormViewModel = getKoin().get()
            val fields by viewModel.fields.collectAsState()

            FormScreen(fields) { isValid ->
                Toast.makeText(this, if (isValid) "Datos válidos" else "Hay errores", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
