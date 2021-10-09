package link.zeljko.presentations.thorntail2quarkus

import io.quarkus.panache.common.Sort
import link.zeljko.presentations.thorntail2quarkus.model.Product
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import java.util.concurrent.CompletionStage
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/products")
class ProductResource(
    val productRepository: ProductRepository,
    @Channel("products-new") val newProductEmitter: Emitter<Long>
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get() =
        productRepository
            .listAll(Sort.by("name"))

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    fun persist(@FormParam("name") name: String): CompletionStage<Long>? {
        val product = Product().apply { this.name = name }
        productRepository.persist(product)
        return newProductEmitter.send(product.id).thenApply { product.id }
    }
}