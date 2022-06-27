package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.WebApp.lib.services.Services

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/")
class HomeController {

    @Autowired
    private lateinit var service : Services


    @GetMapping
    fun GetProducts(model: Model) : String  {

            runBlocking {
                try {
                    withTimeout(GETS_TIMEOUTS){

                        //return@withTimeout ResponseEntity(service.getProducts().second,HttpStatus.OK)
                        return@withTimeout "index"
                    }
                }catch (e: TimeoutCancellationException){
                    //return@runBlocking ResponseEntity(null,HttpStatus.REQUEST_TIMEOUT)
                    return@runBlocking "index"
                }
            }
        return "defaultView"
    }
}