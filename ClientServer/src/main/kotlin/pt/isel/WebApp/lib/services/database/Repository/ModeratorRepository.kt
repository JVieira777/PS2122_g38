package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.Moderator
import java.util.*

@Repository
interface ModeratorRepository : JpaRepository<Moderator, UUID>{

    @Query("SELECT m FROM Moderator m WHERE m.uid = ?1")
    fun findModeratorbyUid(id: UUID): Moderator
}