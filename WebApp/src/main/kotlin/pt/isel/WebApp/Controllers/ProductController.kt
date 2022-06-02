package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Entity.Product
import pt.isel.WebApp.services.DBService
import pt.isel.WebApp.services.Services
import java.util.*

@RestController
@RequestMapping("/{sid}/product")
class ProductController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{pid}")
    fun GetProduct(@PathVariable("pid") product_id: String) : Optional<Product> = service.DBgetProduct(UUID.fromString(product_id))

    @PostMapping
    fun createProduct(@RequestBody product : Product): ResponseEntity<String> {
        val status = service.DBcreateProduct(product)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetProducts() : List<Product>? = service.DBgetAllProducts()

    @DeleteMapping("/{pid}")
    fun DeleteProduct(@PathVariable("pid") product_id : String) : ResponseEntity<String> {
        val status = service.DBDeleteProduct(UUID.fromString(product_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}