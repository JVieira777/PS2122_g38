package pt.isel.WebApp.Backend.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Backend.Entity.Moderator
import java.util.*

@Repository
interface ModeratorRepository : JpaRepository<Moderator, UUID> {
}