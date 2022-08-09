package pt.isel.WebApp.lib.Controllers.auth

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.services.ConnectionManager
import pt.isel.WebApp.lib.services.Services
import java.util.*


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/auth")
class Authentication {

    @Autowired
    private lateinit var services: Services

    @Autowired
    private lateinit var connectionManager: ConnectionManager

    //login
    @PutMapping("/login")
    fun login(@RequestBody credentialDTO: CredentialDTO)  = runBlocking{
        try {
            val response = connectionManager.validateCredentials(credentialDTO)
            if(response != null){
                val springCookie = ResponseCookie.from("connectionID", response.toString())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(600)
                    .build()

                return@runBlocking  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, springCookie.toString()).build()
            }
        }catch (e : Exception){
            return@runBlocking ResponseEntity("Something went Wrong!", HttpStatus.REQUEST_TIMEOUT)
        }
        return@runBlocking ResponseEntity("Failed to Authenticate!", HttpStatus.OK)
    }
    //logout

    @PutMapping("/logout")
    fun terminateSession(@CookieValue(name = "connectionID", defaultValue = "") connectionId : UUID)  = runBlocking{
        try {

            val response = connectionManager.killConnection(connectionId)

            val deleteSpringCookie = ResponseCookie
                .from("connectionID", "")
                .build()

            return@runBlocking ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString()).build()

        }catch (e : Exception){
            return@runBlocking ResponseEntity("Something went Wrong!", HttpStatus.REQUEST_TIMEOUT)
        }
        return@runBlocking ResponseEntity("Failed to Authenticate!", HttpStatus.OK)
    }

}