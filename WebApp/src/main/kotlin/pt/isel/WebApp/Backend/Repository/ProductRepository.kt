package pt.isel.WebApp.Backend.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Backend.Entity.Product
import java.util.*

@Repository
interface ProductRepository : JpaRepository<pt.isel.WebApp.Backend.Entity.Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.sid = ?1")
    fun findAllProductsFromSeller(id: UUID): List<Product>
}