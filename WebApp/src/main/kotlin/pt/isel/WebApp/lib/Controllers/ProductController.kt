package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Product
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Image
import java.util.*
@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/product")
class ProductController {


    /**
     * product -> getProducts()
     * product/id -> getProduct(id)
     * product/id/images -> getProductsImages()
     * product/id/image -> addimage(image)
     */


    @Autowired
    private lateinit var service: Services

    @GetMapping
    fun GetProducts() :  ResponseEntity< List<Product>?> =  runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status = service.getProducts()
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }


    @GetMapping("/{pid}")
    fun GetProduct(@PathVariable("pid") product_id: String) : ResponseEntity<Product> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS){
                val status =service.getProduct(UUID.fromString(product_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }


    @GetMapping("/search/{name}")
    fun GetProductsByName(@PathVariable("name") product_name: String) : ResponseEntity< List<Product>?> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS){
                val status =service.getProductsByname(product_name)
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PostMapping
    fun createProduct(@RequestBody product : Product): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status =service.addProduct(product)
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @DeleteMapping("/{pid}")
    fun DeleteProduct(@PathVariable("pid") product_id : String) : ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.deleteProduct(UUID.fromString(product_id))
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

    @PutMapping("{pid}/update")
    fun updateProduct(@PathVariable("pid") product_id : String, product: Product) = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.updateProduct(UUID.fromString(product_id),product )
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




    @GetMapping("/{pid}/images")
    fun getProductImages(@PathVariable("pid") product_id: String) = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.getProductImages(UUID.fromString(product_id))
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

    @PostMapping("/{pid}/images")
    fun addImage(@PathVariable("pid") product_id: String, image: Image)  = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = service.addImage(image)
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