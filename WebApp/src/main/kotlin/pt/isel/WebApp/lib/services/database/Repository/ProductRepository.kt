package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Product
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.sid = ?1")
    fun findAllProductsFromSeller(id: UUID): List<Product>
}