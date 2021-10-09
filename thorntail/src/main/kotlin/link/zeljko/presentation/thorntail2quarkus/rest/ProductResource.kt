package link.zeljko.presentation.thorntail2quarkus.rest

import link.zeljko.presentations.thorntail2quarkus.model.Product
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/products")
/*
class ProductResource @Inject constructor (
    val productRepository: ProductRepository,
) {
*/
class ProductResource {

    @Inject
    protected lateinit var productRepository: ProductRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get() = productRepository.listAll()

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    fun persist(@FormParam("name") name: String): Long {
        val product = Product().apply { this.name = name }
        productRepository.persist(product)
        return product.id!!
    }
}