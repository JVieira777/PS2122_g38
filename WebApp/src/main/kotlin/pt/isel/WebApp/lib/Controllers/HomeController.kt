package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Product
import java.util.*

@RestController
@RequestMapping("/")
class HomeController {

    @Autowired
    private lateinit var service : Services


    @GetMapping
    fun GetProducts() : ResponseEntity<  List<Product>?> = runBlocking{
        try {
            withTimeout(GETS_TIMEOUTS){

                return@withTimeout ResponseEntity(service.getProducts().second,HttpStatus.OK)
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null,HttpStatus.REQUEST_TIMEOUT)
        }
    }
}