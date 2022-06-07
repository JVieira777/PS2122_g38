package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Seller
import java.util.*

@Repository
interface SellerRepository : JpaRepository<Seller, UUID> {
}