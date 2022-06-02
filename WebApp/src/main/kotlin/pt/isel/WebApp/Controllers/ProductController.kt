package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.services.database.Entity.Product
import pt.isel.WebApp.services.database.DBService
import java.util.*

@RestController
@RequestMapping("/{sid}/product")
class ProductController {
    @Autowired
    lateinit var dbService: DBService

    @GetMapping("/{pid}")
    fun GetProduct(@PathVariable("pid") product_id: String) : Optional<Product> = dbService.getProduct(UUID.fromString(product_id))

    @PostMapping
    fun createProduct(@RequestBody product : Product): ResponseEntity<Boolean> {
        val status = dbService.createProduct(product)
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetProducts() : List<Product>? = dbService.getAllProducts()

    @DeleteMapping("/{pid}")
    fun DeleteProduct(@PathVariable("pid") product_id : String) : ResponseEntity<Boolean> {
        val status = dbService.DeleteProduct(UUID.fromString(product_id))
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }
}