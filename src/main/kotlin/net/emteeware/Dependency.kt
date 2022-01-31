package net.emteeware

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.qute.TemplateData

@TemplateData
data class Dependency(
    @JsonProperty("name") val name: String,
    @JsonProperty("groupId") val groupId: String,
    @JsonProperty("artifactId") val artifactId: String,
    @JsonProperty("version") val version: String,
    @JsonProperty("projectUrl") val projectUrl: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("licenses") val licenses: MutableList<License>,
    @JsonProperty("downloadUrls") val downloadUrls: MutableList<String>,
)

data class License(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String,
)