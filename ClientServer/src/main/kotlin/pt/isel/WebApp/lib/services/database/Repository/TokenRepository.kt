package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Token

import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, UUID> {
    @Query("SELECT t FROM Token t WHERE t.uid = ?1")
    fun findTokenbyUid(id: UUID): Token
}