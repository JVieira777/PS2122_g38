package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Services
import java.util.UUID

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/client")
class ClientController {


    @Autowired
    private lateinit var services: Services


    @PutMapping("/register")
    fun register(@RequestBody clientDTO: ClientDTO)  = runBlocking{
       try {
           val response = services.newClient(clientDTO)
           if(response.first){
               return@runBlocking ResponseEntity(response.second,HttpStatus.OK)
           }
           return@runBlocking ResponseEntity(response.second,HttpStatus.OK)

       }catch (e : Exception){
           return@runBlocking ResponseEntity("Something went Wrong!",HttpStatus.REQUEST_TIMEOUT)
       }
    }

    //newToken
    @PutMapping("/{id}/newToken")
    fun addToken(@PathVariable("id") userId: UUID)  = runBlocking{
        try {
            val response = services.newToken(userId)
            if(response != null){
                return@runBlocking ResponseEntity("New token generated with code: $response",HttpStatus.OK)
            }
            return@runBlocking ResponseEntity("failed to create token",HttpStatus.OK)

        }catch (e : Exception){
            return@runBlocking ResponseEntity("Something went Wrong!",HttpStatus.REQUEST_TIMEOUT)
        }
    }

    //getUserTokens
    @GetMapping("/{id}/tokens")
    fun getUserTokens(@PathVariable("id") userId: UUID)  = runBlocking{
        try {
            val response = services.getUserTokens(userId)
            if(response.isNotEmpty()){
                return@runBlocking ResponseEntity(response,HttpStatus.OK)
            }
            return@runBlocking ResponseEntity("No tokens found",HttpStatus.OK)

        }catch (e : Exception){
            return@runBlocking ResponseEntity("Something went Wrong!",HttpStatus.REQUEST_TIMEOUT)
        }
    }


}

