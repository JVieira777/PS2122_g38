package pt.isel.WebApp.lib.services.database

import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.services.database.Entities.Client
import pt.isel.WebApp.lib.services.database.Repositories.ClientRepository
import pt.isel.WebApp.lib.services.database.Repositories.CredentialRepository
import pt.isel.WebApp.lib.services.database.Repositories.TokenRepository
import java.util.UUID


@Service
class DBService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var credentialRepository: CredentialRepository

    @Autowired
    lateinit var tokenRepository: TokenRepository


    //client

    suspend fun addClient(name: String, email: String, password: String): Boolean = coroutineScope{
        try {
            val clientID = UUID.randomUUID()
            clientRepository.newClient(clientID, name,email, password)
            //clientRepository.save(Client(UUID.randomUUID(),"teste"))
            return@coroutineScope true
        }catch (e: Exception){
            return@coroutineScope false
        }
        return@coroutineScope false
    }



}
