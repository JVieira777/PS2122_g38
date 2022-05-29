package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Entity.Exchange
import pt.isel.WebApp.services.DBService
import java.util.*

@RestController
@RequestMapping("/exchange")
class ExchangeController {
        @Autowired
        lateinit var dbService: DBService

        @GetMapping("/{eid}")
        fun GetExchange(@PathVariable("eid") ex_id: String) : Optional<Exchange> = dbService.getExchange(UUID.fromString(ex_id))

        @PostMapping
        fun createExchange(@RequestBody ex : Exchange): ResponseEntity<Boolean> {
            val status = dbService.createExchange(ex)
            return if (status) {
                ResponseEntity(true, HttpStatus.OK)
            } else {
                ResponseEntity(false, HttpStatus.BAD_REQUEST)
            }
        }

        @GetMapping
        fun GetExchanges() : List<Exchange>? = dbService.getAllExchanges()

        @DeleteMapping("/{eid}")
        fun DeleteExchange(@PathVariable("eid") ex_id: String) : ResponseEntity<Boolean> {
            val status = dbService.DeleteExchange(UUID.fromString(ex_id))
            return if (status) {
                ResponseEntity(true, HttpStatus.OK)
            } else {
                ResponseEntity(false, HttpStatus.BAD_REQUEST)
            }
        }

    @GetMapping("/User/{uid}")
    fun GetAllExchangesFromUser(@PathVariable("uid") user_id: String) : List<Exchange> = dbService.getAllExchangesFromUser(UUID.fromString(user_id))
}