package net.emteeware

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import java.util.*
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/legal")
class LegalResource {

    @Inject
    lateinit var legal: Template

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun legal(): TemplateInstance {
        val propertyFile = this.javaClass.getResourceAsStream("/runtime-resources/project.properties")!!
        val p = Properties()
        p.load(propertyFile)
        val trimmedisotimestamp = p.getProperty("display.timestamp")
        val timestamp = "${trimmedisotimestamp.substring(0, 4)}-${trimmedisotimestamp.substring(4, 6)}-${trimmedisotimestamp.substring(6, 11)}:${trimmedisotimestamp.substring(11, 13)}:${trimmedisotimestamp.substring(13, 16)}"
        val version = p.getProperty("display.version")

        val xml = this.javaClass.getResourceAsStream("/runtime-resources/attribution.xml")!!
        val mapper = XmlMapper()
        val dependencyList : DependencyList = mapper.readValue(xml)

        return legal.data("dependency", dependencyList.dependencies).data("version", version).data("timestamp", timestamp)
    }
}