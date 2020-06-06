# eMTee Showdown

## Table of Contents
* [What is it good for?](#purpose)
* [Will there be more?](#future)
* [How do I run it?](#properties)
* [Quarkus Framework](#quarkus)


<a name="purpose"/>

## What is it good for?

Currently, eMTee Showdown solves one very specific problem: Help choose the next tv show to watch.

To do so, visiting `/update` retrieves the content of a [trakt.tv](https://www.trakt.tv) list containing seasons of tv shows. (There are some hardcoded sample shows so that it also works without a trakt.tv account for demonstration purposes.)

The retrieved seasons are then available at the `/seasons` endpoint. Everybody taking part in the choosing can select seasons to be added to the selection pool.

![](/documentation/seasons%20endpoint.gif)

The `/selection` endpoint will display all selected seasons as face-down cards than can be dismissed or chosen.

![](/documentation/selection%20endpoint.gif)

<a name="future"/>

## Will there be more?

Sure. If I don't lose my drive. Some ideas can be found in the [issues](https://github.com/methom/emtee-showdown/issues) or in the [release planning](https://github.com/methom/emtee-showdown/projects/1).

I am also thinking about user management, workflows, automatic selection modes and much more. If you have ideas, feel free to add them as an [issue](https://github.com/methom/emtee-showdown/issues/new).

If everything goes real well, I also have some ideas to generalize this service to a universal decision-making helper by allowing to configure the use of arbitrary searches for the selections. We'll see.

<a name="properties"/>

## How do I run it?

To run, eMTee showdown needs the following properties:
* trakt.api-key from https://trakt.tv/oauth/applications/new
* trakt.bearer-token, see https://trakt.docs.apiary.io/#reference/authentication-devices (user authentication shall be integrated into this service in the future)
* trakt.user-name, so it knows where to look for the list
* trakt.list-name, so it know which list to look for

You can provide these values in different ways, see https://quarkus.io/guides/config. One example to start it during development would be:

`mvn -Dtrakt.api-key=XXXXXXXXXXXX "-Dtrakt.bearer-token=Bearer XXXXXXXXXXXX" -Dtrakt.user-name=emteeware -Dtrakt.list-name=test-is quarkus:dev`

<a name="quarkus"/>

## Quarkus Framework

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

### Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `emtee-showdown-0.1-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/emtee-showdown-0.1-SNAPSHOT-runner.jar`.

### Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/emtee-showdown-0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .