package net.emteeware

import javax.inject.Singleton

@Singleton
class DependencyList {
    var dependencies = mutableListOf<Dependency>()
}
