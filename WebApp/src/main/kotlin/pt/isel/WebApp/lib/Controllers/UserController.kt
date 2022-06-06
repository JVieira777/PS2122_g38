package pt.isel.WebApp.lib.Controllers

import org.hibernate.pretty.MessageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.User
import pt.isel.WebApp.lib.services.Services
import java.util.*

@RestController
@RequestMapping("/user")
class UserController {
    /**
     * /user -> getusers()
     * /user/id getuser(id)
     * /user/id/exchanges getUserExcahnges(id)
     * /user/id/exchanges/id
     */



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

    @PutMapping("/{uid}")
    fun UpdateUser(@PathVariable("uid") user_id: String,@RequestBody user : User): ResponseEntity<String> {
        val status = service.updateUser(UUID.fromString(user_id),user)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }


}