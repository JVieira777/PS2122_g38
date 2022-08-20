package pt.isel.WebApp.lib.services.Auth
import java.util.*


enum class UserType {
    User,
    Seller,
    Moderator
}


data class LoginResponse (
    val uid: UUID,
    val username: String,
    val emailAddress: String,
    val type: UserType?,
    val tid: UUID,
    val sid: UUID?,
    val mid : UUID?
    )
