package pt.isel.WebApp.lib.services.database

import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.web3j.abi.datatypes.Uint
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.services.database.Entities.Client
import pt.isel.WebApp.lib.services.database.Entities.Token
import pt.isel.WebApp.lib.services.database.Repositories.ClientRepository
import pt.isel.WebApp.lib.services.database.Repositories.CredentialRepository
import pt.isel.WebApp.lib.services.database.Repositories.TokenRepository
import java.util.*
import javax.annotation.PostConstruct


@Service
@Component
class DBService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var credentialRepository: CredentialRepository

    @Autowired
    lateinit var tokenRepository: TokenRepository

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    //client

    suspend fun addClient(name: String, email: String, password: String): Pair<Boolean,String> = coroutineScope{
        val x: java.lang.Object
        try {
            val clientID = UUID.randomUUID()

            val sql = ("call newClient('$clientID','$name','$email','$password')")
            jdbcTemplate.update(sql)


                return@coroutineScope Pair(true,"Successfully registered")

        }catch (e: UncategorizedSQLException){

            println(e.message)
            return@coroutineScope Pair(false,"email already in use")
        }catch (e : Exception){

        }
        return@coroutineScope Pair(false,"Failed to create account")
    }

    suspend fun validateCredentials(email: String,password: String): Pair<Boolean, Pair<String, UUID?>> = coroutineScope{
        try {
            val credential = credentialRepository.getById(email)
            if(credential==null){
                return@coroutineScope Pair(false, Pair("email not found",null))
            }
            if(credential.password.hashCode() == password.hashCode()){
                return@coroutineScope Pair(true,Pair("valid credentials",credential.client_id));
            }
            return@coroutineScope Pair(false,Pair("invalid password",null))
        }catch (e: Exception){
            println("DBService::validateCredentials: $e")
            return@coroutineScope Pair(false, Pair("Exception thrown",null))
        }
        return@coroutineScope return@coroutineScope Pair(false, Pair("Something went wrong",null))

    }

    suspend fun addToken(userId: UUID): UUID? = coroutineScope{

        try {
            val code = UUID.randomUUID()
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH,1)
            //val code: UUID, val expiration: Date, val num_calls : Int, val status : String, val client_id: UUID
            val token = Token(code, calendar.time,0 ,"Active",userId)
            tokenRepository.save(token)
            return@coroutineScope code
        }catch (e : Exception){
            println(e)
        }
        return@coroutineScope null
    }

    suspend fun getUserTokens(userId: UUID)  = coroutineScope{
        try {
            val toRet = tokenRepository.findAll()
            return@coroutineScope toRet.filter { it.client_id == userId }
        }catch (e : Exception){

        }
    }


    suspend fun hasToken(tokenID: UUID) = coroutineScope {
        try {
            val token = tokenRepository.findById(tokenID);
            if(!token.isEmpty){
                return@coroutineScope true
            }
            return@coroutineScope false
        }catch (e : Exception){
            println("dbService::hasToken($tokenID) -> $e")
            return@coroutineScope false;
        }
    }

}
