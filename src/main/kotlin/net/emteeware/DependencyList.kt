package net.emteeware

import javax.inject.Singleton

@Singleton
class DependencyList {
    val dependencyList: ArrayList<Dependency> = ArrayList()

    init {
        reset()
    }

    private fun reset() {
        dependencyList.add(Dependency(1,"Test dependency 1"))
        dependencyList.add(Dependency(2,"Test dependency 2"))
    }
}
