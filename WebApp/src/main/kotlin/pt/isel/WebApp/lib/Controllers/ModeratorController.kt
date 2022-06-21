package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Moderator
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/user/{uid}/mod")
class ModeratorController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{mid}")
    fun GetModerator(@PathVariable("mid") mod_id: String) : ResponseEntity<Moderator> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity(service.getModerator(UUID.fromString(mod_id)).second,HttpStatus.OK)
            }

        }   catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PostMapping
    fun createModerator(@RequestBody mod: Moderator): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status = service.createModerator(mod)
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity("Failed to create Moderator", HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping
    fun GetModerators() : ResponseEntity<List<Moderator>?> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS) {
                return@withTimeout ResponseEntity(service.getModerators().second, HttpStatus.OK)
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }


    @DeleteMapping("/{mid}")
    fun DeleteModerator(@PathVariable("mid") mod_id: String) : ResponseEntity<String> = runBlocking{
        try {
            withTimeout(GETS_TIMEOUTS) {
                val status = service.deleteModerator(UUID.fromString(mod_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PutMapping("/{mid}")
    fun UpdateModerator(@PathVariable("mid") mod_id: String,@RequestBody moderator: Moderator): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status = service.updateModerator(UUID.fromString(mod_id),moderator)
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

}