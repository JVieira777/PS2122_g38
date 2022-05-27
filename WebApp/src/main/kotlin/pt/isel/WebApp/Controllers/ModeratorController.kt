package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Entity.Moderator
import pt.isel.WebApp.services.DBService
import java.util.*

@RestController
@RequestMapping("/user/{uid}/mod")
class ModeratorController {
    @Autowired
    lateinit var dbService: DBService

    @GetMapping("/{mid}")
    fun GetModerator(@PathVariable("mid") mod_id: String) : Optional<Moderator> = dbService.getModerator(UUID.fromString(mod_id))

    @PostMapping
    fun createModerator(@RequestBody mod: Moderator): ResponseEntity<Boolean> {
        val status = dbService.createModerator(mod)
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetModerators() : List<Moderator>? = dbService.getAllModerators()

    @DeleteMapping("/{mid}")
    fun DeleteModerator(@PathVariable("mid") mod_id: String) : ResponseEntity<Boolean> {
        val status = dbService.DeleteModerator(UUID.fromString(mod_id))
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }
}