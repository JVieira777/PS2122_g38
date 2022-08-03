package pt.isel.WebApp.lib.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID>{

    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1 AND u.password = ?2")
    fun login(email: String,password: String): User
}