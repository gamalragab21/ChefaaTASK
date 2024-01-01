# ChefaaTASK Android App

## Brief

ChefaaTASK is an offline-first Android app that allows users to view and manage a list of images fetched from the Marvel REST API. Users can modify captions for each image, locally add new images from the device gallery, and resize images while maintaining their aspect ratios.

 # Screenshots

<img src="https://user-images.githubusercontent.com/67482991/147862490-e8dca851-c23e-4e98-bdf1-e655f4d96973.jpeg" width="200">&nbsp;

<img src="https://user-images.githubusercontent.com/67482991/147862548-b073f263-2886-4aaf-b916-cd6bbfc9b36a.png" width="200">&nbsp;

<img src="https://user-images.githubusercontent.com/67482991/147862610-4bd6bbe5-aad5-4b83-b30e-ddfd2ca51cda.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862611-4a6c0997-bf55-4a8b-8c94-228b795bc314.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862613-933a9e53-5c56-4aef-914e-a9f5a1693932.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862614-0b63b032-0bbc-4064-93c6-a0f07021c9fe.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862615-c86d1a3d-bb8b-4a42-86d9-1c0481ac0eb9.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862616-c233c37f-5326-457c-9487-a91574ea7b98.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862617-7182cf7b-48bb-4dcf-9b5c-306cbc7d4c3c.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862618-a7b4b31c-107d-4b4f-a800-2d0db85a2a88.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862619-25bf3114-fa3c-40a7-97cf-0ca9866dafce.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862620-bc12bbed-5017-47f2-8433-8479788a8e26.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862621-089edd1a-3f75-4beb-8521-fcede29cdd57.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862622-f6248a9b-ff70-4b3e-a878-ceac20ca2995.jpeg" width="200">&nbsp;

<img src="https://user-images.githubusercontent.com/67482991/147862623-d6ac2494-79c0-427c-bce0-4e3a999d4dba.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862624-94140a85-d042-4bc2-a461-89ef5de87cf1.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862625-a76d996c-2678-49f4-a17a-f2c3b7dce187.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862626-ad4b0ad6-fbe9-4459-935c-4b9c040c2445.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862627-b20ef45a-4c0d-4d4f-b971-0bd2b9a8bf78.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862628-ac9af307-f1db-4b9a-b430-4b83b81348e8.jpeg" width="200">&nbsp;
<img src="https://user-images.githubusercontent.com/67482991/147862630-a4258428-a1b3-440b-aade-1b1012017f79.jpeg" width="200">&nbsp;


## Features

- **Image Loading:** The app fetches images from the Marvel REST API.

- **Image Resizing:** Users can resize images through the TinyPNG REST API while maintaining their aspect ratios.

- **Caption Modification:** Users can add a textual caption that is stored locally for each image.

- **Local Caching:** Images and their captions are cached locally using SQLite, RoomDB, or similar technologies.

- **Background Uploads:** Image uploads are offloaded to the background with a notification indicator. If the app is in the foreground, a global status indicator is visible on all app pages.

- **Offline Functionality:** The app works seamlessly offline, and tasks like editing captions or resizing images are queued to run when the connection is restored.

## Main Pages

### 1. Listing Page

- Displays all images obtained from the Marvel REST API.
- Image Previews List: Each item shows the caption beneath the image. If no caption exists, it defaults to "No Caption."
- Search Input: Users can search for images by caption, displaying matching Image Previews.
- Swipe to Refresh: Fetches data from both the server and the local cache.
- Floating Action Bar (FAB): Allows users to add a new image from the device gallery with a caption, reusing the image detail UI.

### 2. Image Detail Page

- Shows the image with an editable text input for the caption.
- Two editable text inputs for the width and height of the image.
- Submit Button: Resizes the image if needed and updates the caption from the text box (if any).
- Background color based on the dominant accent color of the image.
- If no caption exists, it defaults to "No Caption" with a red border around the text input.

### Additional Features

- Long-pressing images shows a menu to save to the device gallery with the caption in the image file metadata.
- Users can add gallery images to the app from in-app-FAB or a share intent.


## Technical Skills Utilized

### Programming Language
- **Kotlin:** The entire app is developed using the Kotlin programming language.

### User Interface
- **XML:** XML is used for defining the layouts and views in the Android user interface.

### Concurrency
- **Coroutines:** Utilized for managing asynchronous tasks and improving code readability.

### Reactive Programming
- **Flow:** Implemented to handle asynchronous data streams and manage UI updates.

### Image Loading
- **Fresco (Figitalk Signature):** Used for efficient image loading, including support for Figitalk Signature.

### Data Encoding
- **Base64:** Employed for encoding and decoding binary data, especially for image-related operations.

### Networking
- **Retrofit:** Utilized for making network requests to the Marvel and TinyPNG APIs.

### Dependency Injection
- **Dagger Hilt:** Implemented for dependency injection, promoting modular and scalable code.

### Local Database
- **Room DB:** Used for local caching of images and their captions, adhering to clean architecture principles.

### Background Processing
- **Job Scheduler:** Employed for background tasks such as image uploading with notification indicators.

### Services and Broadcasts
- **Services and Broadcast Receivers:** Utilized for handling background tasks and communication between components.

### Architecture
- **Clean Architecture:** Adopted to create a modular, maintainable, and scalable codebase.

### Navigation
- **Navigation Components:** Integrated for navigation between different screens and ensuring a smooth user experience.

### Design Patterns
- **MVI (Model-View-Intent):** Followed to structure and manage UI-related code.

## Prerequisites

### APIs

1. **Marvel API**
   - Documentation: [Marvel API Documentation](https://developer.marvel.com/documentation/getting_started)
   - Base URL: `https://gateway.marvel.com`

2. **TinyPNG API**
   - Documentation: [TinyPNG API Reference](https://tinypng.com/developers/reference)

## How to Clone

To clone the ChefaaTASK Android project into Android Studio, follow these steps:

1. Open Android Studio.
2. Click on "File" in the top menu.
3. Select "New" and then click on "Project from Version Control."
4. Choose "Git" from the dropdown menu.
5. In the "Repository URL" field, enter: `https://github.com/gamalragab21/ChefaaTASK`
6. Click "Clone" to clone the repository to your local machine.

Now you have successfully cloned the ChefaaTASK Android project. Happy coding!
