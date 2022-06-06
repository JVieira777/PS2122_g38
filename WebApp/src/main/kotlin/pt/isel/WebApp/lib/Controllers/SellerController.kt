package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Seller
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@RestController
@RequestMapping("/seller")
class SellerController {
    /**
     * id -> getSeller(id)
     * id/products -> getSellerProducts
     * id/exchanges ->getSellerExchanges
     */



    @Autowired
    private lateinit var service: Services

    @GetMapping("/{sid}")
    fun GetSeller(@PathVariable("sid") seller_id: String) : Optional<Seller> = service.getSeller(UUID.fromString(seller_id))

    @PostMapping
    fun createSeller(@RequestBody seller : Seller): ResponseEntity<String> {
        val status = service.createSeller(seller)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{sid}/products")
    fun getSellerProducts(@PathVariable("sid") seller_id: String) = service.getSellerProducts(UUID.fromString(seller_id))

    @GetMapping
    fun GetSellers() : List<Seller>? = service.getSellers()

    @DeleteMapping("/{sid}")
    fun DeleteSeller(@PathVariable("sid") seller_id: String) : ResponseEntity<String> {
        val status = service.deleteSeller(UUID.fromString(seller_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{sid}")
    fun UpdateSeller(@PathVariable("sid") seller_id: String,@RequestBody seller: Seller): ResponseEntity<String> {
        val status = service.updateSeller(UUID.fromString(seller_id),seller)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}