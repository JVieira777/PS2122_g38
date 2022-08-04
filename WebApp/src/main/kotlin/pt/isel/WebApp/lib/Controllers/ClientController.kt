package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Services

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/client")
class ClientController {


    @Autowired
    private lateinit var services: Services


    @PutMapping("/register")
    fun register(@RequestBody clientDTO: ClientDTO)  = runBlocking{
       try {

       }catch (e : Exception){

       }
    }



}

