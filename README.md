# Weather App

This is a weather application that provides real-time weather information for user selected location and allows to search and add new locations to view weather details. The app utilizes the Weather API (https://www.weatherapi.com) to fetch weather data and the GeoDB Cities API (https://rapidapi.com/wirefreethought/api/geodb-cities) for location search functionality. Firebase is used for real-time data storage and synchronization.

## Features

- Display current weather information for the user's selected locations
- Search and add new locations to view weather details
- View hourly temperature forecast for the selected day
- Display average temperature, wind speed, and humidity for the next 5 days

## APIs Used

- Weather API (https://www.weatherapi.com): Provides weather data for specified locations
- GeoDB Cities API (https://rapidapi.com/wirefreethought/api/geodb-cities): Enables location search functionality

## Technologies Used

- Android SDK
- Java programming language
- Firebase Realtime Database: Stores user's saved locations and weather data

## Getting Started

To run the Weather App on your local machine, follow these steps:

1. Clone the repository:
   ```
   git clone https://github.com/amitm144/weatherApp.git
   ```

2. Open the project in Android Studio.

3. Set up Firebase:
   - Create a new Firebase project at https://console.firebase.google.com
   - Add your Android app to the Firebase project
   - Download the `google-services.json` file and place it in the `app` directory of the project

4. Obtain API keys:
   - Sign up for a free account at https://www.weatherapi.com and get your API key
   - Sign up for a free account at https://rapidapi.com and subscribe to the GeoDBCities API to get your API key

5. Update API keys:
   - Open the `gradle.properties` file in the project's root directory
   - Add the following lines, replacing `YOUR_WEATHER_API_KEY` and `YOUR_GEODB_API_KEY` with your actual API keys:
     
     ```
     WEATHER_API_KEY=YOUR_WEATHER_API_KEY
     GEODB_API_KEY=YOUR_GEODB_API_KEY
     ```

6. Build and run the app on an Android device or emulator.

## Usage

- To add a new location, tap on the search icon and enter the desired location in the search bar. Select the location from the search results to add it to the saved locations.
- Tap on a saved location to view its weather details.
- On the weather details screen, you can view the hourly temperature forecast for the selected day by scrolling through the hours.
- The average temperature, wind speed, and humidity for the next 5 days are also displayed on the weather details screen.
