[![Build Status](http://ci.hypothermic.nl/buildStatus/icon?job=javacogs/master)](https://ci.hypothermic.nl/job/javacogs/job/master/)
[![Download](https://img.shields.io/badge/download-latest-blue.svg)](https://ci.hypothermic.nl/job/javacogs/job/master/lastSuccessfulBuild/artifact/target/)
[![BSD 3-Clause License](https://img.shields.io/badge/license-BSD%203--Clause-lightgrey.svg)](https://github.com/hypothermic/javacogs/blob/master/LICENSE)

# Javacogs

A professional client library for the Discogs API v2.  
Compatible with all Java platforms including Android.

# Features

- Asynchronous networking and response
- Easy authentication with one-time setup
- Built-in adaptive rate limiting
- Handler library structure for ease of use
- Support for Java 6 and up (and Kotlin, of course!)
- No external dependencies except for the JSON parser

# Try it out

Retrieve information about a release by ID:

```java
int releaseId = 249504; // ID of Rick Astley - Never Gonna Give You Up

Javacogs.getInstance().getHandler(Handler.DATABASE)
.getReleaseById(releaseId, new ResponseCallback<Release>() {
    public void onResult(Response<Release> response) {
        if (response.hasSucceeded()) {
            System.out.println(response.getValue().toString());
        } else {
            System.out.println("Response failed.");
        }
    }
});
```

More examples are in the [examples](./src/examples/) directory.  
Don't worry, they have proper documentation for beginners!

### Maven dependency
```xml
<repository>
  <id>javacogs-mvn-repo</id>
  <url>https://raw.github.com/hypothermic/javacogs/mvn-repo/</url>
</repository>

<dependency>
  <groupId>nl.hypothermic</groupId>
  <artifactId>javacogs</artifactId>
  <version>[1.0.18,)</version>
</dependency>
```

### Gradle dependency
```gradle
repositories {
    maven {
        url "https://raw.github.com/hypothermic/javacogs/mvn-repo/"
    }
}

dependencies {
    // Note: use 'api' instead of 'compile' if you're using Android Studio.
    compile group: 'nl.hypothermic', name: 'javacogs', version: '1.0.17-RC1'
}
```

# Authentication

You can authenticate to Discogs using the Discogs Auth Flow:

Authenticate using a **Token**:

```java
Javacogs client = new Javacogs();
client.setAuthenticationMethod(new TokenAuthenticationMethod("YOUR-TOKEN"));
```

Authenticate using a **Key** and **Secret**:

```java
client.setAuthenticationMethod(new KeySecretAuthenticationMethod("KEY", "SECRET"));
```

To use **OAuth**, you will need to implement the `AuthenticationMethod` interface in your own implementation.  
Then, simply call `client.setAuthenticationMethod(new YourOAuthImplementation(...));`

# Handler structure

At first glance, the handler system might seem a little complicated.
You will soon find out that this design choice is extremely easy to use though.
Here's a structure mapping to get you started:

```
new Javacogs() -> DatabaseHandler() -> getEntitiesBySearch()
                                    -> getReleaseById()
                                    -> getReleasesByArtist()
                                    -> getReleasesByLabel()
                                    -> getMasterById()
                                    -> getArtistById()
                                    -> getLabelById()
                                    
               -> UserCollectionHandler() -> getFolderById()
                                          -> getFoldersByUser()
                                          -> getFolderContents()
                                          -> getCollectionValue()
                                          -> deleteFolderById()
                                          -> addReleaseToFolder()
                                          -> deleteReleaseFromFolder()
                                             
               -> UserIdentityHandler() -> getProfileByUsername()
                                        -> getUserSubmissions()
```