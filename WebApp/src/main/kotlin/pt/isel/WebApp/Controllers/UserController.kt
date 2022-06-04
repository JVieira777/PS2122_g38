package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.Services
import pt.isel.WebApp.lib.database.Entity.Exchange
import pt.isel.WebApp.lib.database.Entity.User
import java.util.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var service: Services

    @GetMapping("/{uid}")
    fun GetUser(@PathVariable("uid") user_id: String) : Optional<User> = service.getUser(UUID.fromString(user_id))

    @PostMapping
    fun createUser(@RequestBody user : User): ResponseEntity<String> {
        val status = service.createUser(user)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetUsers() : List<User>? = service.getUsers()

    @DeleteMapping("/{uid}")
    fun DeleteUser(@PathVariable("uid") user_id: String) : ResponseEntity<String> {
        val status = service.deleteUser(UUID.fromString(user_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("{uid}/exchanges")
    fun getUserExchanges(@PathVariable("uid") user_id: String) : List<Exchange> = service.getUserExchanges(UUID.fromString(user_id))

    @GetMapping("{uid}/products")
    fun getUserExchanges(@PathVariable("uid") user_id: String) : List<Exchange> = service.getUserProducts(UUID.fromString(user_id))

}