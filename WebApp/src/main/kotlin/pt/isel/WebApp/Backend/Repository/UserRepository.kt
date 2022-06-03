package pt.isel.WebApp.Backend.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Backend.Entity.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<pt.isel.WebApp.Backend.Entity.User, UUID> {
}