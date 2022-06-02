package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.services.database.Entity.Seller
import pt.isel.WebApp.services.database.DBService
import java.util.*

@RestController
@RequestMapping("/user/{uid}/seller")
class SellerController {
    @Autowired
    lateinit var dbService: DBService

    @GetMapping("/{sid}")
    fun GetSeller(@PathVariable("sid") seller_id: String) : Optional<Seller> = dbService.getSeller(UUID.fromString(seller_id))

    @PostMapping
    fun createSeller(@RequestBody seller : Seller): ResponseEntity<Boolean> {
        val status = dbService.createSeller(seller)
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetSellers() : List<Seller>? = dbService.getAllSellers()

    @DeleteMapping("/{sid}")
    fun DeleteSeller(@PathVariable("sid") seller_id: String) : ResponseEntity<Boolean> {
        val status = dbService.DeleteSeller(UUID.fromString(seller_id))
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }
}
