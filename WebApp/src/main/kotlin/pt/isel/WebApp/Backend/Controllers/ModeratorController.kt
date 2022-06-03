package pt.isel.WebApp.Backend.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Backend.Entity.Moderator
import pt.isel.WebApp.Backend.services.Services
import java.util.*

@RestController
@RequestMapping("/user/{uid}/mod")
class ModeratorController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{mid}")
    fun GetModerator(@PathVariable("mid") mod_id: String) : Optional<Moderator> = service.DBgetModerator(UUID.fromString(mod_id))

    @PostMapping
    fun createModerator(@RequestBody mod: Moderator): ResponseEntity<String> {
        val status = service.DBcreateModerator(mod)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetModerators() : List<Moderator>? = service.DBgetAllModerators()

    @DeleteMapping("/{mid}")
    fun DeleteModerator(@PathVariable("mid") mod_id: String) : ResponseEntity<String> {
        val status = service.DBDeleteModerator(UUID.fromString(mod_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}