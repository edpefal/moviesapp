# README

## Moviesc App

### Project Overview

MovieApp is a modular Android application that uses The Movie Database (TMDb) API to display movie information. It is built using Clean Architecture principles and a feature-based modular design to enhance maintainability, scalability, and testability.


### Architecture

The application follows a Clean Architecture approach, with a clear separation between the UI, domain, and data layers. Each module has the following layers


- **Presentation Layer**:
  - **View (UI)**: Composable functions and UI components built with Jetpack Compose.
  - **ViewModel**: Handles UI-related data and business logic, communicating with UseCases.

- **Domain Layer**:
  - **UseCases**: Encapsulate specific business logic and interact with the repository.
  - **Repository Definitions**: Defines the contracts that will be used by the Repository Implementation.

- **Data Layer**:
  - **Repository Implementation**: Manages data operations, handling data sources (network, database).
  - **Data Sources**: API service for network operations.

#### Modules
The project is divided into several feature modules:

- **app**: The main module that ties all other modules together.
- **moviesmanager**: Contains common utilities, network, and database configurations for retrieving information about movies.
- **favorites**: Contains domain and presentation layers for saving movies into the DB.
- **moviedetail**: Contains domain and presentation layers for displaying information about a specific movie.
- **nowplaying**: Contains domain and presentation layers for displaying information about movies that are currently playing in movie theaters.
- **popular**: Contains domain and presentation layers for displaying information about movies that are popular right now.
- **shared**: Contains data and presentation layers with classes that are common to the rest of the modules.

```kotlin
   Movies App

├── app
│   ├── moviesmanager
│   └── favorites
│   └── moviedetail
│   └── nowplaying
│   └── popular
│   └── shared


```





### Design Decisions

1. **Architecture Choice**: Clean Architecture ensures a scalable, maintainable, and testable codebase with a clear separation of concerns.


2. **Jetpack Compose**: Used for building the UI due to its modern declarative approach and integration with Kotlin.


3. **Kotlin Coroutines**: Used for managing asynchronous tasks to simplify threading and improve readability.


4. **Dependency Injection**: Hilt is used for dependency injection to manage dependencies and improve testability.

### UI Design

1. **Main Screen**: The main container of the app, showing a bottom navigation for navigate between screens.

2. **MovieListScreen**:A screen that is reused to show popular movies, now playing movies and favorite movies.

3. **MovieDetailScreen**: Allows the user to check information about a certain movie, and save it to the db.


# Installation
To get started with the project, follow these steps:

- Clone this repository to your local machine using the following command
  ```
  git clone https://github.com/edpefal/moviesapp.git
  ```
- Open the project in Android Studio or your preferred IDE.

- Build and run the project on an Android emulator or physical device.


