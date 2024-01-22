<h1 align="center">Playpal 🎮</h1>

<p align="center">
  Android app to know more about your favorite games! <br>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://app.circleci.com/pipelines/github/reyghifari/UserGitHubAppMVVM"><img alt="Build Status" src="https://github.com/skydoves/Pokedex/workflows/Android%20CI/badge.svg"/></a> <br>
</p>

<p align="center">  
  PlayPal implements Modularization, Dependency Injection, Reactive Programming and Clean Architecture.
</p>
</br>

## Source API 🛜

PlayPal uses the [RAWG API](https://rawg.io/apidocs) for its data. The API provides detailed data about thousands of games available on the market.

## Continuous Integration 📲

![image](https://github.com/ranggarahman/Play-Pal/assets/79177708/3c2b0fc6-725c-43ae-a0bc-2ae4fec558fa)

PlayPal uses GitHub Actions to implement continous integration. The tests being run [here](https://github.com/ranggarahman/Play-Pal/actions) includes running Unit Tests and Generating the APK to check for a successful build.

## Architecture 👷🏻
**PlayPal** is based on the Clean Architecture, which is based on [Google's official architecture guidelines](https://developer.android.com/topic/architecture).

Here is the illustration of how Clean Architecture is implemented in PlayPal:

<div align="center">
  <img src="https://github.com/ranggarahman/Play-Pal/assets/79177708/77536986-75a4-4922-98b8-7cb8be9e846d" alt="Clean Architecture in PlayPal" width="400">
</div>

The names listed in the diagram represents the classes that are used in building PlayPal.

## Dependency Injection 💉
Dependency Injection in PlayPal uses Hilt ⚔️. Dependency modules are separated individually and injected into the classes that require them for functioning.

## Database Encryption 🧑🏻‍💻

PlayPal implements database encryption using SQLCipher 🥷🏻.

## Multi Module 📦

PlayPal implements Modularization. There are 3 modules, namely, app, core and favorite.
- App Module is mostly responsible for displaying data and handling View
- Core Module is responsible for business logic and data transaction
- Favorite Module is a dynamic module reponsible for keeping track of games listed as favorite by the user.
