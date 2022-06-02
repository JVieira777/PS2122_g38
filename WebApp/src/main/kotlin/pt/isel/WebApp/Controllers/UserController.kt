package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Entity.Image
import pt.isel.WebApp.Entity.User
import pt.isel.WebApp.services.DBService
import pt.isel.WebApp.services.Services
import java.util.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var service: Services

    @GetMapping("/{uid}")
    fun GetUser(@PathVariable("uid") user_id: String) : Optional<User> = service.DBgetUser(UUID.fromString(user_id))

    @PostMapping
    fun createUser(@RequestBody user : User): ResponseEntity<String> {
        val status = service.DBcreateUser(user)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetUsers() : List<User>? = service.DBgetAllUsers()

    @DeleteMapping("/{uid}")
    fun DeleteUser(@PathVariable("uid") user_id: String) : ResponseEntity<String> {
        val status = service.DBDeleteUser(UUID.fromString(user_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

}