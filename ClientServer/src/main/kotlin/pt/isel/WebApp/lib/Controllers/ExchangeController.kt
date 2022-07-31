package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Exchange
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Product

import java.util.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/exchange")
class ExchangeController {
    /**
     * get/delete/update/add id ->get/delete/update/addExchange(id)
     */



    @Autowired
    private lateinit var services: Services

    @GetMapping("/{eid}")
    fun getExchange(@PathVariable("eid") ex_id: String) = runBlocking{
        services.getExchange(ex_id.toLong())
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping
    fun createExchange(@RequestBody exchange: Exchange): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                return@withTimeout ResponseEntity( services.createExchange(exchange).second, HttpStatus.OK)
            }
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity(services.createExchange(exchange).second, HttpStatus.REQUEST_TIMEOUT)
        }
    }
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping
    fun GetExchanges() : List<Exchange>? = runBlocking {
        var ret : List<Exchange>? = null
        try{
            withTimeout(GETS_TIMEOUTS){
                ret = services.getExchanges().second
            }
        }catch (e : TimeoutCancellationException){
                return@runBlocking ret
        }
        return@runBlocking ret
    }
    /*
    @CrossOrigin(origins = ["http://localhost:3000"])
    @PutMapping("/{eid}")
    fun CompleteExchange(@PathVariable("eid") ex_id: String) : ResponseEntity<String>  = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status = services.completeExchange(ex_id.toLong())
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity("Exchange not found", HttpStatus.OK)
                }
            }
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity("Something went wrong", HttpStatus.REQUEST_TIMEOUT)
        }


    }*/
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/User/{uid}")
    fun GetAllExchangesFromUser(@PathVariable("uid") user_id: String) : ResponseEntity<List<Exchange>> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity<List<Exchange>>(services.getUserExchanges(UUID.fromString(user_id)).second, HttpStatus.OK)
            }
        }catch ( e : TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }

    }


    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/Seller/{uid}")
    fun GetAllExchangesFromSeller(@PathVariable("uid") seller_id: String) : ResponseEntity<List<Exchange>> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity<List<Exchange>>(services.getSellerExchanges(UUID.fromString(seller_id)).second, HttpStatus.OK)
            }
        }catch ( e : TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }

    }
}