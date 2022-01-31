package net.emteeware

import io.quarkus.qute.TemplateData

@TemplateData
data class Dependency(
    val id: Int,
    val name: String
)