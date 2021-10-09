package link.zeljko.presentations.thorntail2quarkus

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import link.zeljko.presentations.thorntail2quarkus.model.Product
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductRepository : PanacheRepository<Product> {
}