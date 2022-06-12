package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Exchange
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Product
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
        fun getExchange(@PathVariable("eid") ex_id: String) : Optional<Exchange> = services.getExchange(UUID.fromString(ex_id))


        @PostMapping
        fun createExchange(@RequestBody user_id: UUID, product_id: UUID, quantity : Int): ResponseEntity<String> {
            //val status = services.createExchange(user_id,product_id,quantity)

            //val status = services.createExchange(ex)
            /*return if (status.equals("Success")) {
                ResponseEntity(status, HttpStatus.OK)
            } else {
                ResponseEntity(status, HttpStatus.BAD_REQUEST)
            }*/
            return ResponseEntity<String>("a", HttpStatus.OK)
        }

        @GetMapping
        fun GetExchanges() : List<Exchange>? = services.getExchanges()

        @PutMapping("/{eid}")
        fun CompleteExchange(@PathVariable("eid") ex_id: String) : ResponseEntity<String> {
            val status = services.completeExchange(UUID.fromString(ex_id))
            return if (status.equals("Success")) {
                ResponseEntity(status, HttpStatus.OK)
            } else {
                ResponseEntity(status, HttpStatus.BAD_REQUEST)
            }
        }

    @GetMapping("/User/{uid}")
    fun GetAllExchangesFromUser(@PathVariable("uid") user_id: String) : List<Exchange> = services.getUserExchanges(UUID.fromString(user_id))
}