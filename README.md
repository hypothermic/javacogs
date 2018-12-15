[![Build Status](http://ci.hypothermic.nl/buildStatus/icon?job=javacogs/master)](https://ci.hypothermic.nl/job/javacogs/job/master/)
[![Download](https://img.shields.io/badge/download-latest-blue.svg)](https://ci.hypothermic.nl/job/javacogs/job/master/lastSuccessfulBuild/artifact/target/)
[![BSD 3-Clause License](https://img.shields.io/badge/license-BSD%203--Clause-lightgrey.svg)](https://github.com/hypothermic/javacogs/blob/master/LICENSE)

# Javacogs

A professional asynchronous client library for the Discogs API v2.

# Features
- Asynchronous networking and response
- Easy authentication with one-time setup
- Built-in adaptive rate limiting
- Handler library structure for ease of use

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
