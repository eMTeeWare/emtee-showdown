package net.emteeware

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import com.fasterxml.jackson.module.kotlin.readValue

@Path("/legal")
class LegalResource {

    @Inject
    lateinit var legal: Template

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun legal(): TemplateInstance {
        val xml = javaClass.classLoader.getResourceAsStream("/META-INF/resources/attribution.xml")!!
        val mapper = XmlMapper()
        val dependencyList : DependencyList = mapper.readValue(xml)
        return legal.data("dependency", dependencyList.dependencies)
    }
}