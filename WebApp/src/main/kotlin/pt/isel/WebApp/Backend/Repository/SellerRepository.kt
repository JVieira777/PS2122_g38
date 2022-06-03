package pt.isel.WebApp.Backend.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Backend.Entity.Seller
import java.util.*

@Repository
interface SellerRepository : JpaRepository<pt.isel.WebApp.Backend.Entity.Seller, UUID> {
}