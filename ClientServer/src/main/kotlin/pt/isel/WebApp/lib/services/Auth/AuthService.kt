package pt.isel.WebApp.lib.services.Auth

import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.services.database.Entity.Moderator
import pt.isel.WebApp.lib.services.database.Entity.Token
import pt.isel.WebApp.lib.services.database.Entity.User
import pt.isel.WebApp.lib.services.database.Repository.ModeratorRepository
import pt.isel.WebApp.lib.services.database.Repository.SellerRepository
import pt.isel.WebApp.lib.services.database.Repository.TokenRepository
import pt.isel.WebApp.lib.services.database.Repository.UserRepository
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class AuthService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var tokenRepository: TokenRepository

    @Autowired
    lateinit var sellerRepository: SellerRepository

    @Autowired
    lateinit var moderatorRepository: ModeratorRepository

    suspend fun login(email : String, password:String) : Pair<Boolean, LoginResponse?> = coroutineScope {
        val user = userRepository.login(email,password).orElse(null)
        println(user.toString())
        if(user == null){
            return@coroutineScope Pair(false,null)
        }
        val token = Token(uid = user.id)
        createToken(token)
        val userType = CheckTypeofUser(user)
        if(userType.first == UserType.Seller){
        return@coroutineScope Pair(true,
            LoginResponse(uid = user.id, username = user.username, emailAddress = user.emailAddress, type = userType.first, tid = token.id, sid = userType.second,null)
        )
        }else if(userType.first == UserType.Moderator){
            return@coroutineScope Pair(true,
                LoginResponse(uid = user.id, username = user.username, emailAddress = user.emailAddress, type = userType.first, tid = token.id, null,userType.second)
            )
        }
        return@coroutineScope Pair(true,
            LoginResponse(uid = user.id, username = user.username, emailAddress = user.emailAddress, type = userType.first, tid = token.id,null,null)
        )
    }


    suspend fun createToken(token: Token) : Pair<Boolean, String> = coroutineScope{
        return@coroutineScope try {
            tokenRepository.save(token)
            return@coroutineScope Pair(true,"Token added successfully")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Token Exception: ${e.message}")
        }
    }


    suspend fun CheckTypeofUser(user : User) : Pair<UserType, UUID> = coroutineScope{


            val seller = sellerRepository.findSellerbyUid(user.id).orElse(null)

            if(seller!=null){
                println(seller.toString())
                return@coroutineScope Pair(UserType.Seller,seller.id)
            }
            val moderator = moderatorRepository.findModeratorbyUid(user.id).orElse(null)
            if(moderator != null){
                return@coroutineScope Pair(UserType.Moderator,moderator.id)
            }
        return@coroutineScope Pair(UserType.User,user.id)

    }

    suspend fun getTokenbyId(id: UUID) : Pair<Boolean, Token> = coroutineScope {
        return@coroutineScope Pair(true,tokenRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }

}