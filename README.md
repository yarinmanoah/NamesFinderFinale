# Seminar Project

The NameFinder Project is an Android-based application integrated with an API service designed to manage and manipulate name-related data.
Users can interact with the app to search, add, categorize, or delete names.
The project consists of two primary components:

The NameFinder Android Application: A mobile app that communicates with the NameAPI to provide functionalities like searching names by letter, category, or fetching a random name.

The NameAPI: A backend service built using Flask and MongoDB to store, retrieve, and manipulate name data.

The API supports operations such as:

Listing all names
Filtering names by first letter
Categorizing names
Adding and deleting names
Fetching a random name

The API endpoints are designed to handle different operations with names, making it a comprehensive service for managing name-related tasks in the mobile application.
A full-stack application combining a Flask API backend deployed on Vercel, MongoDB database, and an Android native application.

## Project Overview

This project consists of three main components:
- A RESTful API built with Flask (Python)
- Database management with MongoDB Compass
- Android native application (Java)

## Tech Stack

### Backend
- Flask (Python)
- MongoDB Compass
- Vercel (Deployment)

### Frontend/Mobile
- Android Studio
- Java
- Android SDK

## Installation & Setup

### Prerequisites
- Python 3.x
- MongoDB Compass
- Android Studio
- VS Code (or preferred IDE for Python)
- Vercel CLI

### Backend Setup

1. Clone the repository
```bash
git clone <your-repository-url>
cd <project-directory>/backend
```

2. Create and activate virtual environment
```bash
python -m venv venv
source venv/bin/activate  # For Unix/macOS
venv\Scripts\activate     # For Windows
```

3. Install dependencies
```bash
pip install -r requirements.txt
```

4. Set up environment variables
Create a `.env` file in the backend directory:
```env
MONGODB_URI=your_mongodb_connection_string
SECRET_KEY=your_secret_key
```

### MongoDB Setup

1. Install MongoDB Compass
2. Connect to your MongoDB instance using the connection string
3. Create a new database and required collections

### Android App Setup

1. Open Android Studio
2. Open the `android` directory as a project
3. Sync Gradle files
4. Update the API base URL in `constants.java` or configuration file

## Running the Project

### Running the Flask API Locally

```bash
cd backend
flask run
```
The API will be available at `http://localhost:8088`

### Running the Android App

1. Open the project in Android Studio
2. Select your target device (emulator or physical device)
3. Click the "Run" button or press Shift + F10

## Deployment

### Deploying the Flask API to Vercel

1. Install Vercel CLI
```bash
npm install -g vercel
```

2. Login to Vercel
```bash
vercel login
```

3. Deploy the API
```bash
cd backend
vercel
```

4. Update the API URL in your Android application to point to the deployed Vercel URL

## API Endpoints

Document your API endpoints here. For example:

```
GET /api/items - Retrieve all items
POST /api/items - Create new item
GET /api/items/:id - Retrieve specific item
PUT /api/items/:id - Update specific item
DELETE /api/items/:id - Delete specific item
```

## Project Structure

```
project/
├── backend/
│   ├── app/
│   │   ├── routes/
│   │   ├── models/
│   │   └── utils/
│   ├── requirements.txt
│   └── vercel.json
├── android/
│   ├── app/
│   │   ├── src/
│   │   └── build.gradle
│   └── gradle/
└── README.md
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Video Demonstration


## License

This project is licensed under the MIT License - see the LICENSE file for details
Copyright (c) 2024 Yarin Manoah


## Contact

Yarin Manoah - yarinmanoah1443@gmail.com
Project Link: https://github.com/yarinmanoah/NamesFinderFinale
