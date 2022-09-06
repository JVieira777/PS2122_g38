package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

//@CrossOrigin(origins = ["http://localhost:3000"])
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
    private lateinit var services: Services

    //@GetMapping("/{uid}")
    //fun GetUser(@PathVariable("uid") user_id: String) : Optional<User> = service.getUser(UUID.fromString(user_id))

    @GetMapping("/{uid}")
    fun GetUser(@PathVariable("uid") user_id: String): ResponseEntity<User> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.getUser(UUID.fromString(user_id))
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

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<String> = runBlocking {

        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.createUser(user)
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

    @GetMapping
    fun GetUsers(): ResponseEntity<List<User>> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.getUsers()
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

    @DeleteMapping("/{uid}")
    fun DeleteUser(@PathVariable("uid") user_id: String): ResponseEntity<String> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.deleteUser(UUID.fromString(user_id))
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

    @PutMapping("/{uid}")
    fun UpdateUser(@PathVariable("uid") user_id: String, @RequestBody user: User): ResponseEntity<String> =
        runBlocking {
            try {
                withTimeout(POST_TIMEOUTS) {
                    val status = services.updateUser(UUID.fromString(user_id), user)
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