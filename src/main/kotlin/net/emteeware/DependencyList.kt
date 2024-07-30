package net.emteeware

import jakarta.inject.Singleton

@Singleton
class DependencyList {
    var dependencies = mutableListOf<Dependency>()
}
