package pt.isel.WebApp.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Entity.Product
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
}