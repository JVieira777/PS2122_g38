package pt.isel.WebApp.lib.services.database.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entities.Token
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, UUID> {

    @Query("select e from Token e where e.client_id = ?1")
    fun getUserTokens( clientId: UUID) : List<Token>

}