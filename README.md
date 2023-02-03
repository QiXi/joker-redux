# Joker-Redux [![Release](https://jitpack.io/v/QiXi/joker-redux.svg?style=flat-square)](https://jitpack.io/#QiXi/joker-redux)
Implementation of the redux library in Kotlin

## Usage

```kotlin
import qixi.jokerredux.Store
import qixi.jokerredux.RootReducer
import qixi.jokerredux.Dispatcher

// Actions
interface AppActions
// Store
class AppStore : Store<AppState, AppActions>(AppReducer(), AppState())
// Reducer
class AppReducer : RootReducer<AppState, AppActions>()
// ActionCreator
class ViewerActionCreator(dispatcher: Dispatcher<AppActions>) : AppActionCreator(dispatcher) {

    fun load(tag: String) {
        dispatcher.dispatch(...action)
    }
}
```

## Setup
1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
} 
```
2. Add the dependency
```groovy
dependencies {
    // Joker-Redux
    implementation 'com.github.QiXi:joker-redux:0.1.2'
}
```