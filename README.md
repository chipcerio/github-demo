[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

# Github Users
This is a **work-in-progress** Github users app, that connects the sources to
[Github REST API V3](https://developer.github.com/v3/?). It is still in development stage.

The app contains a Master/Detail flow type UI and uses RxJava with MVVM architecture.

### Android development
The app uses these libraries, tools, and patterns. As a summary:

 * app is written entirely in [Kotlin](https://kotlinlang.org/)
 * uses [RxJava2](https://github.com/ReactiveX/RxJava)
 * uses [Retrofit](http://square.github.io/retrofit/) for REST API interface
 * uses [Gson](https://github.com/google/gson) for JSON serialization/deserialization
 * uses a simple dependency injection
 * uses [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture pattern

### Motivation
To keep myself updated to any libraries, tools, or patterns that can be applied to an android app

### Development setup
Requires the latest Android Studio 3.0 (or newer) to be able to build the app.

### Code Style
This project follows the **official code style** from [kotlinlang.org](https://kotlinlang.org/docs/reference/coding-conventions.html),
[Android Kotlin Style Guide](https://android.github.io/kotlin-guides/style.html).
It uses [ktlint](https://ktlint.github.io/) for Kotlin linter and
[spotless](https://github.com/diffplug/spotless) for code formatting and fixing

### Contributions
If you've found an error, you may file an issue.