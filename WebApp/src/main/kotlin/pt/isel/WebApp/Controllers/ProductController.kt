package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import pt.isel.WebApp.lib.Services
import pt.isel.WebApp.lib.database.Entity.Product
import java.util.*

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{pid}")
    fun getProduct(@PathVariable("pid") product_id: String) : Optional<Product> = service.getProduct(UUID.fromString(product_id))

    @PostMapping
    fun createProduct(@RequestBody product : Product): ResponseEntity<String> {
        val status = service.addProduct(product)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetProducts() : List<Product>? = service.getProducts()

    @DeleteMapping("/{pid}")
    fun DeleteProduct(@PathVariable("pid") product_id : String) : ResponseEntity<String> {
        val status = service.deleteProduct(UUID.fromString(product_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}