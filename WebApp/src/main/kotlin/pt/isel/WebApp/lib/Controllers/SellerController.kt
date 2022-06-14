package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
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
    fun GetSeller(@PathVariable("sid") seller_id: String) : ResponseEntity<Seller> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.getSeller(UUID.fromString(seller_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }


    @PostMapping
    fun createSeller(@RequestBody seller : Seller): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.createSeller(seller )
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping("/{sid}/products")
    fun getSellerProducts(@PathVariable("sid") seller_id: String) = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.getSellerProducts(UUID.fromString(seller_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping
    fun GetSellers() : ResponseEntity<List<Seller>> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.getSellers()
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @DeleteMapping("/{sid}")
    fun DeleteSeller(@PathVariable("sid") seller_id: String) : ResponseEntity<String> =runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.deleteSeller(UUID.fromString(seller_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PutMapping("/{sid}")
    fun UpdateSeller(@PathVariable("sid") seller_id: String,@RequestBody seller: Seller): ResponseEntity<String> =runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.updateSeller(UUID.fromString(seller_id),seller)
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }
}