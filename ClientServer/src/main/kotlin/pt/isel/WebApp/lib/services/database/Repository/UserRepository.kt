package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID>