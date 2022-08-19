package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Auth.LoginResponse
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var services: Services


    @GetMapping("/login")
    fun Login(@RequestParam("email") email_address: String,@RequestParam("password") password: String,) : ResponseEntity<LoginResponse> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.login(email_address,password)
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