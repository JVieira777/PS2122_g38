package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Exchange
import java.util.*

@Repository
interface ExchangeRepository : JpaRepository<Exchange, Long> {


    @Query("SELECT e FROM Exchange e WHERE e.client_id = ?1 OR e.seller_id = ?1")
    fun GetAllUserExchanges(id: UUID): List<Exchange>

}