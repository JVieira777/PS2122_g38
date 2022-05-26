package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Entity.Image
import pt.isel.WebApp.Entity.User
import pt.isel.WebApp.services.DBService
import java.util.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var dbService: DBService

    @GetMapping("/{uid}")
    fun GetUser(@PathVariable("uid") user_id: String) : Optional<User> = dbService.getUser(UUID.fromString(user_id))

    @PostMapping
    fun createUser(@RequestBody user : User): ResponseEntity<Boolean> {
        val status = dbService.createUser(user)
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetUsers() : List<User>? = dbService.getAllUsers()

    @DeleteMapping("/{uid}")
    fun DeleteUser(@PathVariable("uid") user_id: String) : ResponseEntity<Boolean> {
        val status = dbService.DeleteUser(UUID.fromString(user_id))
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

}