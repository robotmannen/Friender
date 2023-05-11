# Friender

Friender is an Android app coded in Kotlin that allows users to browse fictitious people fetched from an API and view liked friends on another page. The app utilizes Room database as a local storage mechanism. 

## Features

- Browse fictitious people fetched from API
- View liked friends on another page
- Pressing a liked friend brings up more information about them
- Uses Room database for local storage
- Includes unit tests to ensure code quality and stability
- Includes UI tests to ensure app functionality

## Usage

- On the main screen, you can browse through fictitious people fetched from the API.
- You can like or dislike a friend by tapping on the corresponding button. Liked friends will appear on the second screen.
- On the second screen, you can view a list of liked friends.
- You can click on a friend to view more information about them.

## Testing

- The project contains unit tests located in the `test` directory. These tests can be run using Android Studio's built-in testing tools.
- The project also includes UI tests located in the `androidTest` directory. These tests can be run using Android Studio's built-in testing tools as well.
