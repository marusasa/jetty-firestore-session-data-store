# Jetty Session Data Store with Google Cloud Firestore

This is an example implementation of "org.eclipse.jetty.server.session.SessionDataStore".

It uses Google Cloud Datastore (Firesotre) for session data persistent layer.

This example application starts Javalin server, which uses Jetty server internally.

I created this for a project that uses Javalin server. So far it is working OK for me. 
However, it is not a complete implementation of the session data store.
Do not assume that this will work for every use cases.

## Technology Stack

- Java 21
- Javalin Server
- Jetty Server
- Google Cloud Firestore

## How to run the application locally

- Create a project in Google Cloud.
- Set the following environment variables so the application can connect to your google cloud Firestore datastore.
	- GCLOUD_PROJECT	
	- GOOGLE_APPLICATION_CREDENTIALS
- Start ServerMain.java. 

## Test the application

Access http://localhost:8080 to test the application.

When successful, you should see the session record created in your '(default)' database in Google Cloud project.