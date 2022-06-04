package pt.isel.WebApp.Backend.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Backend.Entity.Exchange
import pt.isel.WebApp.Backend.services.Services
import java.util.*

@RestController
@RequestMapping("/exchange")
class ExchangeController {

        @Autowired
        private lateinit var services: Services

        @GetMapping("/{eid}")
        fun GetExchange(@PathVariable("eid") ex_id: String) : Optional<Exchange> = services.getExchange(UUID.fromString(ex_id))

        @PostMapping
        fun createExchange(@RequestBody ex : Exchange): ResponseEntity<String> {
            val status = services.createExchange(ex)
            return if (status) {
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


}