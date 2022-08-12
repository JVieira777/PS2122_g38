package pt.isel.WebApp.lib.services

import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.services.database.DBService
import java.util.*
import kotlin.collections.HashMap

@Component
class ConnectionManager {

    @Autowired
    lateinit var dbService : DBService
    //connectionID,userID
    val connections: HashMap<UUID, ConnectionData> = HashMap()

    fun newConnection(userId: UUID?) : UUID?  {
        val connectionData = userId?.let { ConnectionData(it, Date(System.currentTimeMillis()+600000)) }
        val connectionId = UUID.randomUUID()
        if (connectionData != null) {
            connections.put(connectionId,connectionData)
            return connectionId
        }
        return null
    }

    fun getConnection(connectionId: UUID): ConnectionData?{
        val connection = connections.get(connectionId)
        if (connection != null) {
            if(connection.expiration < Date(System.currentTimeMillis())){
                return connection
            }
        }
        return null
    }


    suspend fun validateCredentials(credentialDTO: CredentialDTO) : UUID? = coroutineScope {
        try {

            val result = dbService.validateCredentials(credentialDTO.email,credentialDTO.password)
            if(result.first){
                return@coroutineScope createNewConnection(result.second.second)
            }
            return@coroutineScope null
        }catch (e: Exception){
            return@coroutineScope null
        }
        return@coroutineScope null
    }

    suspend fun createNewConnection(clientId : UUID?) : UUID? = coroutineScope{
        if(clientId != null){
            val connectionData = ConnectionData(clientId, Date(System.currentTimeMillis()+600000))
            val connectionId = UUID.randomUUID()
            connections.put(connectionId,  connectionData)
            return@coroutineScope connectionId
        }
        return@coroutineScope null
    }

    suspend fun killConnection(connectionID : UUID) : Boolean = coroutineScope {
        if(connections.containsKey(connectionID)){
            connections.remove(connectionID)
            return@coroutineScope true;
        }
        return@coroutineScope false;
    }
}

data class ConnectionData(val userId: UUID, val expiration: Date)