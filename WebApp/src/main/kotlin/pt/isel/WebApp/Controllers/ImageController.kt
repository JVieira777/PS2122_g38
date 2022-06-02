package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.services.database.Entity.Image
import pt.isel.WebApp.services.database.DBService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

@RestController
@RequestMapping("/image")
class ImageController () {
    @Autowired
    lateinit var dbService: DBService

    @GetMapping("/{iid}")
    fun GetImage(@PathVariable("iid") image_id: String) : Optional<Image> = dbService.getImage(UUID.fromString(image_id))

    @PostMapping
    fun createImage(@RequestBody image : Image): ResponseEntity<Boolean> {
        val status = dbService.createImage(image)
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetImages() : List<Image>? = dbService.getAllImages()

    @DeleteMapping("/{iid}")
    fun DeleteImage(@PathVariable("iid") image_id: String) : ResponseEntity<Boolean> {
        val status = dbService.DeleteImage(UUID.fromString(image_id))
        return if (status) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{pid}")
    fun GetAllImagesFromAProduct(@PathVariable("pid") image_pid: String) : List<Image>? = dbService.GetallImageFromAProduct(UUID.fromString(image_pid))

}