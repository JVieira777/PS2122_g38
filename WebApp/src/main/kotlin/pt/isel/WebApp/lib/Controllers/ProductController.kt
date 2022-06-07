package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Product
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Image
import java.util.*

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
    fun GetProducts() : List<Product>? = service.getProducts()


    @GetMapping("/{pid}")
    fun GetProduct(@PathVariable("pid") product_id: String) : Optional<Product> = service.getProduct(UUID.fromString(product_id))

    @PostMapping
    fun createProduct(@RequestBody product : Product): ResponseEntity<String> {
        val status = service.addProduct(product)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{pid}")
    fun DeleteProduct(@PathVariable("pid") product_id : String) : ResponseEntity<String> {
        val status = service.deleteProduct(UUID.fromString(product_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("{pid}/update")
    fun updateProduct(@PathVariable("pid") product_id : String, product: Product) = service.updateProduct(UUID.fromString(product_id), product)



    @GetMapping("/{pid}/images")
    fun getProductImages(@PathVariable("pid") product_id: String)  = service.getProductImages(UUID.fromString(product_id))

    @PostMapping("/{pid}/images")
    fun addImage(@PathVariable("pid") product_id: String, image: Image)  = service.addImage(image)


}