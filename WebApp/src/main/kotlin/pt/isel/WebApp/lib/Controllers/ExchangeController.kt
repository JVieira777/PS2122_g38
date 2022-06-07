package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Exchange
import pt.isel.WebApp.lib.services.Services
import java.util.*

@RestController
@RequestMapping("/exchange")
class ExchangeController {
    /**
     * get/delete/update/add id ->get/delete/update/addExchange(id)
     */


    @Autowired
        private lateinit var services: Services

        @GetMapping("/{eid}")
        fun GetExchange(@PathVariable("eid") ex_id: String) : Optional<Exchange> = services.getExchange(UUID.fromString(ex_id))

        @PostMapping
        fun createExchange(@RequestBody ex : Exchange): ResponseEntity<String> {
            val status = services.createExchange(ex)
            return if (status.equals("Success")) {
                ResponseEntity(status, HttpStatus.OK)
            } else {
                ResponseEntity(status, HttpStatus.BAD_REQUEST)
            }
        }

        @GetMapping
        fun GetExchanges() : List<Exchange>? = services.getExchanges()

        @DeleteMapping("/{eid}")
        fun DeleteExchange(@PathVariable("eid") ex_id: String) : ResponseEntity<String> {
            val status = services.deleteExchange(UUID.fromString(ex_id))
            return if (status.equals("Success")) {
                ResponseEntity(status, HttpStatus.OK)
            } else {
                ResponseEntity(status, HttpStatus.BAD_REQUEST)
            }
        }

    @GetMapping("/User/{uid}")
    fun GetAllExchangesFromUser(@PathVariable("uid") user_id: String) : List<Exchange> = services.getUserExchanges(UUID.fromString(user_id))
}