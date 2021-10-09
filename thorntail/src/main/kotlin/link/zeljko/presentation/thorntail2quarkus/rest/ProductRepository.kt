package link.zeljko.presentation.thorntail2quarkus.rest

import link.zeljko.presentations.thorntail2quarkus.model.Product
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ApplicationScoped
//class ProductRepository(@Inject val em: EntityManager) {
class ProductRepository {

    @PersistenceContext
    protected lateinit var em: EntityManager

    fun listAll() =
        em.createQuery("select p from Product p order by p.name", Product::class.java).resultList

    fun persist(product: Product) {
        em.persist(product)
    }
}