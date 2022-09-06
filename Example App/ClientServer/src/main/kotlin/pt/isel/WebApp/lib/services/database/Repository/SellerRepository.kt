package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Seller
import java.util.*

@Repository
interface SellerRepository : JpaRepository<Seller, UUID> {

    @Query("SELECT s FROM Seller s WHERE s.uid = ?1")
    fun findSellerbyUid(id: UUID): Optional<Seller>
}