package pt.isel.WebApp.Backend.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Backend.Entity.Seller
import pt.isel.WebApp.Backend.services.Services
import java.util.*

@RestController
@RequestMapping("/user/{uid}/seller")
class SellerController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{sid}")
    fun GetSeller(@PathVariable("sid") seller_id: String) : Optional<Seller> = service.DBgetSeller(UUID.fromString(seller_id))

    @PostMapping
    fun createSeller(@RequestBody seller : Seller): ResponseEntity<String> {
        val status = service.DBcreateSeller(seller)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetSellers() : List<Seller>? = service.DBgetAllSellers()

    @DeleteMapping("/{sid}")
    fun DeleteSeller(@PathVariable("sid") seller_id: String) : ResponseEntity<String> {
        val status = service.DBDeleteSeller(UUID.fromString(seller_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}