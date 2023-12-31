# ChefaaTASK Android App

## Brief

ChefaaTASK is an offline-first Android app that allows users to view and manage a list of images fetched from the Marvel REST API. Users can modify captions for each image, locally add new images from the device gallery, and resize images while maintaining their aspect ratios.

 # Screenshots

<img src="https://github.com/gamalragab21/ShopApp/assets/67482991/ba696dc7-c1b2-4144-80f7-6ed494a5ebdc
" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/4762155a-ccf5-4328-b669-b0da79507133" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/98f1e2ad-8df4-4135-b6f5-02e7b3755d34" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/487a4252-e955-4146-b1ed-1616476ab544" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/ceb403cb-5eef-45c3-9796-fdbe73097c16" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/79cd3d17-d193-46fa-81cf-d0dcd4d0a14a" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/15489164-d809-4548-905a-c9736a49d5de" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/43156eac-93c4-4951-aabc-4d0b064bf490" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/954f7c70-7858-4d14-b071-c0f22879f32f" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/bf3d6c68-4e79-4037-b02b-e628725c2819" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/5884507c-9397-4c43-9e77-802fd1ecbdc0" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/5224fc3a-be16-4ffa-9645-541c489d46f2" width="200">&nbsp;
<img src="https://github.com/gamalragab21/ChefaaTASK/assets/67482991/fefe7adb-f093-42df-9118-99e1a6e6d41c" width="200">&nbsp;

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
