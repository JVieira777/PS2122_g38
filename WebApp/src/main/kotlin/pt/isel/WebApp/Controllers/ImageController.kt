package pt.isel.WebApp.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.database.Entity.Image

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.WebApp.lib.Services
import java.util.*

@RestController
@RequestMapping("/image")
class ImageController () {
    @Autowired
    private lateinit var service : Services

    @GetMapping("/{iid}")
    fun GetImage(@PathVariable("iid") image_id: String) : Optional<Image> = service.DBgetImage(UUID.fromString(image_id))

    @PostMapping
    fun createImage(@RequestBody image : Image): ResponseEntity<String> {
        val status = service.DBcreateImage(image)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetImages() : List<Image>? = service.DBgetAllImages()

    @DeleteMapping("/{iid}")
    fun DeleteImage(@PathVariable("iid") image_id: String) : ResponseEntity<String> {
        val status = service.DBDeleteImage(UUID.fromString(image_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{pid}")
    fun GetAllImagesFromAProduct(@PathVariable("pid") image_pid: String) : List<Image>? = service.DBGetallImageFromAProduct(UUID.fromString(image_pid))

}