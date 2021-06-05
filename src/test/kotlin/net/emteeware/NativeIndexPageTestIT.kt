package net.emteeware

import io.quarkus.test.junit.NativeImageTest

// How to run
/* Running this test from the IDE might fail on Windows as building the native application might fail.
Build the native app manually as described here:  https://quarkus.io/guides/building-native-image
Provide the necessary application properties as environment variables, e. g. via a run configuration.
 */

@NativeImageTest
class NativeIndexPageTestIT : IndexPageTest()