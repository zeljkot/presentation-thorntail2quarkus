package link.zeljko.presentation.thorntail2quarkus.rest

import org.wildfly.swarm.Swarm
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
class RestApplication : Application()

fun main(args: Array<String>) {
    val swarm = Swarm(false)
    configureDatabase(swarm)
    swarm.start()
    swarm.deploy()
}

private fun configureDatabase(swarm: Swarm) {
    swarm.withProperty("thorntail.datasources.data-sources.DemoDS.password", "demo_pass")
}