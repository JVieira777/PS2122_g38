package pt.isel.WebApp.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.services.database.Entity.Exchange
import java.util.*

@Repository
interface ExchangeRepository : JpaRepository<Exchange, UUID> {


    @Query("SELECT e FROM Exchange e WHERE e.uidA = ?1 OR e.uidB = ?1")
    fun GetAllUserExchanges(id: UUID): List<Exchange>

}