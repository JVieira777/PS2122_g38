package pt.isel.WebApp.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.services.database.Entity.Seller
import java.util.*

@Repository
interface SellerRepository : JpaRepository<Seller, UUID> {
}