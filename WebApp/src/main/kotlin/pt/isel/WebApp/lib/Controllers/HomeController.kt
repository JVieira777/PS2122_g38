package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Product
import java.util.*

@RestController
@RequestMapping("/")
class HomeController {

    @Autowired
    private lateinit var service : Services


    @GetMapping
    fun GetProducts() : List<Product>? = service.getProducts()
}