package pt.isel.WebApp.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.services.database.Entity.Product
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
}