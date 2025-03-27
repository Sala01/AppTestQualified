# 📝 Informe técnico de solución
**Test técnico Android — Qualified.io**  
**Objetivo:** Crear una aplicación móvil Android nativa con arquitectura limpia, validaciones dinámicas y pruebas unitarias.

---

## 📌 Requerimientos clave

1. Simular la respuesta de una API desde un archivo JSON en `/assets/`.
2. Renderizar dinámicamente un formulario en Compose con campos definidos en el JSON.
3. Aplicar validaciones de `maxlength` y `regex`.
4. Mostrar solo campos visibles, ordenados por `order`.
5. Implementar botón de validación al final del formulario.
6. Utilizar MVVM + Clean Architecture.
7. Aplicar buenas prácticas de diseño y pruebas unitarias.

---

## 🧱 Arquitectura utilizada: MVVM + Clean Architecture

Se aplicó una arquitectura modular y escalable con separación de responsabilidades en 3 capas:

```
+-------------------+         +---------------------+         +------------------+
|     presentation  | <-----> |       domain        | <-----> |       data       |
|   (UI, ViewModel) |         | (use cases, models) |         | (sources, models)|
+-------------------+         +---------------------+         +------------------+
```

- **presentation**: Jetpack Compose UI + ViewModel que expone estado observable.
- **domain**: Entidades puras (`Field`) y casos de uso (`GetFieldsUseCase`).
- **data**: Fuente de datos simulada desde `assets/`, modelos `DTO` (`FieldDto`) y mapeo a dominio.

---

## ⚙️ Tecnología y herramientas utilizadas

| Herramienta | Uso |
|-------------|-----|
| **Kotlin** | Lenguaje base |
| **Jetpack Compose** | UI moderna declarativa |
| **Koin** | Inyección de dependencias |
| **Coroutines + Flows** | Programación asíncrona y reactiva |
| **Gson** | Parsear JSON desde assets |
| **JUnit** | Pruebas unitarias |
| **kotlinx-coroutines-test** | Test de flujos y ViewModel |

---

## 📄 Implementación detallada

### ✅ 1. Carga y mapeo de datos

- El archivo `sample.json` se carga como si viniera de una API real.
- Se usó `Gson` para parsear el JSON a un `Map<String, FieldDto>`.
- Se filtraron campos `visible == true` y se ordenaron por `order`.

### ✅ 2. Conversión a modelo de dominio

Se creó una función `toDomain()` que convierte cada `FieldDto` a un `Field`, usando los parámetros necesarios para la UI:
- `key`, `label`, `type`, `maxlength`, `regex`, `values`

### ✅ 3. UI dinámica con Compose

- Renderizado con `TextField`, `Dropdown`, etc.
- Validación en tiempo real por campo.
- Botón de validación general.

### ✅ 4. Inyección con Koin

`AppModule.kt` provee:
- `FormRepositoryImpl`
- `GetFieldsUseCase`
- `FormViewModel`

### ✅ 5. Testing

- `GetFieldsUseCaseTest`: campos visibles y ordenados.
- `FormViewModelTest`: `StateFlow` inicializado.
- `ValidatorTest`: pruebas puras de lógica de validación.

---

## 🧩 Validaciones implementadas

```kotlin
fun validate(field: Field, value: String): String? {
    if (field.maxLength != null && value.length > field.maxLength)
        return "Máximo ${field.maxLength} caracteres"
    if (!field.regex.isNullOrBlank() && !Regex(field.regex).matches(value))
        return "Formato inválido"
    return null
}
```

---

## 🧠 ¿Por qué esta solución?

- Respeta principios **SOLID** y buenas prácticas modernas.
- Modular, fácil de testear, extender y mantener.
- Flujo realista simulando respuesta de API.
- Jetpack Compose + Koin + Coroutines: stack moderno y recomendado.

---

## 📦 Resultado final

- App funcional, escalable y profesional.
- Validaciones dinámicas.
- UI declarativa.
- Código bien estructurado y probado.
