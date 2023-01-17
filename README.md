# YAMLA - Yet Another Movie List App

This is a simple Android application that queries a list of movies from a
custom [API](https://my.api.mockaroo.com/movies.json?key=cb03b960) and shows their details.

## Project Characteristics

This project makes use of the following tools and solutions:

* 100% [Kotlin](https://kotlinlang.org/).
* MVVM design pattern.
* [Android Jetpack](https://developer.android.com/jetpack) components
* Single-activity architecture (similar to SPA in web),
  using [Android Navigation components](https://developer.android.com/guide/navigation/navigation-getting-started)
  to deal fragment transactions.
* Dependency Injection
* Basic permission handling (in this case, it asks for coarse location permission)

## Technical Stack

This project was compiled with SDK 24 as a minimum and SDK 33 as target, as of the date of release.
The tech stack used for this project includes:

* [Kotlin](https://kotlinlang.org/) as the main programming language
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) to perform
  background operations (API calls, UI refresh, etc)
* [Koin](https://insert-koin.io/) for dependency injection
* [Retrofit](https://square.github.io/retrofit/) for networking
* Some [Jetpack](https://developer.android.com/jetpack) components:
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) to handle
      in-app navigation between fragments
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to deal with
      data update in the UI
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an
      action when lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and
      manage UI-related data in a lifecycle conscious way
    * [View Binding](https://developer.android.com/topic/libraries/view-binding) to ease the UI
      integration with the view-relate codebase.
    * [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) to
      deal with paginated data lists
    * [Room](https://developer.android.com/topic/libraries/architecture/room) to handle in-app
      persistence.
* [Glide](https://bumptech.github.io/glide/l) for image loading
* [Mockito](https://site.mockito.org/) for mocking data and classes for unit testing alongside JUnit4
* ... among others.
* Architecture:
    * MVVM as the main design pattern. It was decided to forgo the implementation CLEAN architecture
      due time constraints.
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
      , [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
      , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
      , [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args)
      plugin)
* Gradle:
    * Migrated from Groovy syntax
      to [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for better
      readability and to implement better dependency version-handling in the future.
    * Plugins
      like ([SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args))
      to be used with the Navigation
      components, [Secrets](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin)
      to use API keys and sensitive data in the `local.properties` file,
      and [ktlint-gradle](https://github.com/JLLeitschuh/ktlint-gradle) which is a ktlint wrapper
      for code check and formatting.

## Architecture

Since the application was developed with a single module, package-oriented architecture, the project
itself is structured in the following manner:

* `data`: package containing the data models used in the application, resulting from the API calls,
  as well as entities used for the onboard database. This package also contains the DAO classes and
  the database instance.
* `di`: package containing the dependency-injection modules and object for their initialization.
* `network`: package containing classes dealing with API requests with Retrofit and Interceptors for
  injecting the keys in the requests.
* `repository`: package containing the repository class used for this project, it handles and
  transforms the data obtained from the API. It also contains a Paging data source for
  paginated-lists.
* `ui`: it contains all the packages and classes related to the UI. It is sub-divided in:
    * `activity`: contains the application's `MainActivity` and also the `SplashActivity`.
    * `adapter`: this project uses a generic `DynamicAdapter` in order to reduce the boilerplate
      that implementing the `RecyclerView` is known for. It uses `ItemModel` classes to encapsulate
      the data classes to be used in the lists, `TypeFactory` classes to select the
      corresponding `ViewHolder` and layout, and a base `DiffCallback` object.
    * `fragment`: package containing the relevant fragments and a `BaseFragment` with common
      methods.
    * `viewmodel`: package containing the viewmodel used in the application.
* `utils`: package containing a series of miscelaneous utility classes used throughout the
  application.

## Installation

In order to install this project from Android Studio it is needed to provide an API_KEY (found on
the API url), and add it in the root `local.properties` file, following the same convention:

* API_KEY="your-public-key"
