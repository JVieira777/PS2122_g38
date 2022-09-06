package pt.isel.WebApp.lib.services


import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.isel.WebApp.lib.Controllers.ClientDTO
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.services.blockchain.ExchangeManagerService
import pt.isel.WebApp.lib.services.blockchain.ExchangeService
import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entities.Token
/*import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entity.**/
import java.util.*



@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService



    //val exchangeManager: ExchangeManagerService = ExchangeManagerService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b","0x3593CbEC414E1f96dBd7769Db1237E3E97b06C15")
    val exchangeManager: ExchangeManagerService = ExchangeManagerService("https://goerli.infura.io/v3/18e3b06cbd1a4f4dae9d53af8e438bc9","0x0FdA6dE11D675DAf0bA221bD09fF6FF3DF82Cc26")



    //add
    suspend fun newClient(client: ClientDTO): Pair<Boolean,String> = coroutineScope{
        try {

            return@coroutineScope  dbService.addClient(client.name,client.credential.email, client.credential.password)
        }catch (e: Exception){
            println(e)
            return@coroutineScope Pair(false,"Failed to register")
        }
        return@coroutineScope Pair(false,"Failed to register")
    }

    suspend fun validateCredentials( credentialDTO: CredentialDTO) : UUID? = coroutineScope {
        return@coroutineScope dbService.validateCredentials(credentialDTO.email, credentialDTO.password).second.second
    }

    suspend fun newToken(userid: UUID) : UUID? = coroutineScope {
        return@coroutineScope dbService.addToken(userid)
    }

    suspend fun getUserTokens(userid: UUID) : List<Token> = coroutineScope {
        return@coroutineScope dbService.getUserTokens(userid) as List<Token>
    }

    suspend fun hasToken(tokenID: UUID) : Boolean = coroutineScope{
        return@coroutineScope dbService.hasToken(tokenID)
    }

}