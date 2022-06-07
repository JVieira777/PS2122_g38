package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Moderator
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@RestController
@RequestMapping("/user/{uid}/mod")
class ModeratorController {
    @Autowired
    private lateinit var service: Services

    @GetMapping("/{mid}")
    fun GetModerator(@PathVariable("mid") mod_id: String) : Optional<Moderator> = service.getModerator(UUID.fromString(mod_id))

    @PostMapping
    fun createModerator(@RequestBody mod: Moderator): ResponseEntity<String> {
        val status = service.createModerator(mod)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetModerators() : List<Moderator>? = service.getModerators()

    @DeleteMapping("/{mid}")
    fun DeleteModerator(@PathVariable("mid") mod_id: String) : ResponseEntity<String> {
        val status = service.deleteModerator(UUID.fromString(mod_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{mid}")
    fun UpdateModerator(@PathVariable("mid") mod_id: String,@RequestBody moderator: Moderator): ResponseEntity<String> {
        val status = service.updateModerator(UUID.fromString(mod_id),moderator)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }
}