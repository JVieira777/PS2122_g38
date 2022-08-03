package pt.isel.WebApp.lib.services.Auth

import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.services.database.Entity.User
import pt.isel.WebApp.lib.services.database.Repository.UserRepository

@Service
class AuthService {

    @Autowired
    lateinit var userRepository: UserRepository

    suspend fun login(email : String, password:String) : Pair<Boolean, User> = coroutineScope {
        return@coroutineScope Pair(true,userRepository.login(email,password))
    }
}