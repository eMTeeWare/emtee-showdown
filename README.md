# eMTee Showdown

## What is it good for?

Currently, eMTee Showdown solves one very specific problem: Help choose the next tv show to watch.

To do so, visiting `/update` retrieves the content of a [trakt.tv](https://www.trakt.tv) list containing seasons of tv shows. (There are some hardcoded sample shows so that it also works without a trakt.tv account for demonstration purposes.)

The retrieved seasons are then available at the `/seasons` endpoint. Everybody taking part in the choosing can select seasons to be added to the selection pool.

The `/selection` endpoint will display all selected seasons as face-down cards than can be dismissed or chosen.

## Will there be more?

Sure. If I don't lose my drive. Some ideas can be found in the [issues](https://github.com/methom/emtee-showdown/issues) or in the [release planning](https://github.com/methom/emtee-showdown/projects/1).

I am also thinking about user management, workflows, automatic selection modes and much more. If you have ideas, feel free to add them as an [issue](https://github.com/methom/emtee-showdown/issues/new).

If everything goes real well, I also have some ideas to generalize this service to a universal decision-making helper by allowing to configure the use of arbitrary searches for the selections. We'll see.

## How do I run it?

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