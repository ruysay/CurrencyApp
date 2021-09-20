# CurrencyApp

This App uses MVVM with Clean Architecture and applied Coroutines and Android Jetpack Components ( Room, Navigation Components and Live Data)

We can consider Clean architecture as an extension of MVVM pattern. In most practices of MVVM pattern, ViewModel talks to repositories to fulfill its fuctionalities. However as project scale grows, viewmodels will hold a lot of responsibilities which makes the project tend to less easier to maintain.

MVVM with Clean architecture is suitable for the issue so this app uses the approach as it separates the layers. Presentation Layer, Domain Layer and Data Layer will be separate applications which will provide better reusability for each one of them.


# Tech-Stack

* __Architecture__ : MVVM  + CLEAN
* __Coroutines__ : For background operations like fetching data in DB
* __Room database__ : For offline persistence and Paging Library
* __Live Data__ : To notify view for change
* __Dagger Hilt__ : For dependency injection
* __Language__ : Kotlin

# Architecture Diagram
This application is divided into 3 separate layers:

### 1. Presentation Layer
This includes our `Activity`s, `Fragment`s, and `ViewModel`s.
An UI component like `Activity and Fragment` will talk to a `ViewModel` and a `ViewModel` will talk to the domain layer to perform actions.

*Fragments  
*ViewModels
*LiveData<T>

### 2. Domain Layer
The domain layer contains all the **use cases** of the application..
*UseCases  
*Repository (implemented in Data Layer)

### 3. Data Layer
This layer provides all the repositories which the domain layer can use.
*Network  
*DataBase  
*Memory

# Architecture and Data Flow
The diagram below shows the data flow in this project.
Please note **Remote Data Source**   has not been implemented in the master branch.
![project structure](https://i.imgur.com/qKJlDlo.png)
  
  
  