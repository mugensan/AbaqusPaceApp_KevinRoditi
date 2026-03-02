<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/bd843494-385c-4152-8f6b-96a8b48791ec" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/794dfba8-6d34-47a6-a45e-2733d8d61bb7" width="100%"></td>
  </tr>
</table>


# AbaqusPaceApp_KevinRoditi

# Senior Android Clean Architecture Project

👉 **Versión en Español:** [Ir a la versión en Español](#wealthapp--proyecto-android-clean-architecture-nivel-senior)

---

## 📱 Overview

WealthApp is a production-grade Android application built using modern Android development standards.

The project follows **Clean Architecture** principles with **MVVM**, built entirely with **Jetpack Compose**, and integrates:

- Hilt (Dependency Injection)
- Retrofit + Moshi (Networking)
- Room (Local Caching)
- Coroutines + Flow
- StateFlow (UI State Management)
- Material 3

This project demonstrates senior-level Android development practices suitable for technical interviews and real-world production environments.

---

## 🏗 Architecture

The application is structured following **Clean Architecture** with strict separation of concerns:

```
presentation → UI (Compose, ViewModels, State)
domain → Business logic (Models, UseCases, Repository contracts)
data → Repository implementation, DTOs, Room, API
core → Utilities, network configuration
di → Dependency Injection modules
```

### Architecture Principles Applied

- Single Responsibility Principle (SRP)
- Dependency Inversion Principle (DIP)
- Repository Pattern
- UseCase pattern (one responsibility per use case)
- DTO → Domain → Entity mapping
- No Android dependencies in domain layer
- No DTO exposure outside data layer
- Immutable UI state via StateFlow

---

## 🧰 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM**
- **Hilt**
- **Retrofit**
- **Moshi**
- **OkHttp Logging Interceptor**
- **Room**
- **Coroutines**
- **Flow / StateFlow**
- **Material 3**

---

## 🌐 API Configuration

### Base URL

```
https://api.test.pace.abaqus.cl/
```

### Endpoints Used

```
GET /portfolios/value/?currency=USD
GET /portfolios/performance/ytd/?currency=USD
GET /accounts/cash-balance/
GET /portfolios/returns/ytd/?currency=USD
GET /portfolios/positions/?currency=USD
```

---

## 💾 Local Caching Strategy

- Portfolio data and positions are cached using Room.
- On successful API response → database is updated.
- On API failure → cached data is returned.
- DAO methods return Flow for reactive updates.

---

## 🔄 State Management

UI state is handled using:

```kotlin
sealed interface PortfolioUiState {
    object Loading : PortfolioUiState
    data class Success(
        val portfolio: Portfolio? = null,
        val positions: List<Position> = emptyList()
    ) : PortfolioUiState
    data class Error(val message: String) : PortfolioUiState
}
```

---

## 🚀 How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone <repository-url>
```

### 2️⃣ Open in Android Studio

- Open Android Studio
- Select "Open"
- Choose the project folder

### 3️⃣ Sync Gradle

Wait for Gradle to finish syncing dependencies.

### 4️⃣ Run the application

- Select a device or emulator
- Click **Run**

---

## 📦 Git Strategy

The project follows milestone-based commits:

- `feat: setup clean architecture foundation with hilt integration`
- `feat: implement retrofit network layer with wealth endpoints`
- `feat: implement room caching layer`
- `feat: implement domain use cases and repository contracts`
- `feat: implement portfolio feature with compose and stateflow`

---

## 🧪 Testability

The architecture allows:

- Unit testing of UseCases
- Repository mocking (MockPortfolioRepositoryImpl included)
- ViewModel testing
- Clear dependency injection boundaries

---

## 📌 Senior-Level Considerations

- **Robust Auth**: Senior `AuthInterceptor` handling `Token` and `Bearer` schemes + user association.
- **Mocking Strategy**: Integrated Mock Repository for seamless UI development.
- **Dynamic UI**: Shared navigation and centered titles across all views.
- **Clean Architecture**: Strict separation of concerns and consolidated use cases.

---

# WealthApp – Proyecto Android Clean Architecture Nivel Senior

👉 **English Version:** [Go to English Version](#abaquspaceapp_kevinroditi)

---

## 📱 Descripción General

WealthApp es una aplicación Android de nivel profesional construida utilizando los estándares modernos de desarrollo Android.

El proyecto sigue los principios de **Clean Architecture** con **MVVM**, desarrollado completamente con **Jetpack Compose**, e integra:

- Hilt (Inyección de dependencias)
- Retrofit + Moshi (Networking)
- Room (Persistencia local)
- Coroutines + Flow
- StateFlow (Gestión de estado)
- Material 3

---

## 🏗 Arquitectura

La aplicación está estructurada siguiendo **Clean Architecture**, con separación estricta de responsabilidades:

```
presentation → UI (Compose, ViewModels, State)
domain → Lógica de negocio (Modelos, UseCases, contratos de repositorio)
data → Implementación de repositorio, DTOs, Room, API
core → Utilidades y configuración de red
di → Módulos de inyección de dependencias
```

---

## 📌 Consideraciones Nivel Senior

- **Autenticación Robusta**: `AuthInterceptor` de nivel senior que maneja esquemas `Token` y `Bearer`.
- **Estrategia de Mocking**: Repositorio Mock integrado para desarrollo ágil de UI.
- **Interfaz Dinámica**: Navegación compartida y títulos centrados en todas las vistas.
- **Código Escalable**: Arquitectura lista para producción y preparada para testing.
