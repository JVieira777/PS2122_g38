package pt.isel.WebApp.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Entity.Exchange
import java.util.*

@Repository
interface ExchangeRepository : JpaRepository<Exchange, UUID> {
}