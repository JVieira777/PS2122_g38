package pt.isel.WebApp.lib.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Image
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.WebApp.lib.services.Services
import java.util.*

@RestController
@RequestMapping("/image")
class ImageController () {


    @Autowired
    private lateinit var service : Services

    @GetMapping("/{iid}")
    fun GetImage(@PathVariable("iid") image_id: String) : Optional<Image> = service.getImage(UUID.fromString(image_id))

    @PostMapping
    fun createImage(@RequestBody image : Image): ResponseEntity<String> {
        val status = service.addImage(image)
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun GetImages() : List<Image>? = service.getImages()

    @DeleteMapping("/{iid}")
    fun DeleteImage(@PathVariable("iid") image_id: String) : ResponseEntity<String> {
        val status = service.deleteImage(UUID.fromString(image_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{pid}")
    fun GetAllImagesFromAProduct(@PathVariable("pid") image_pid: String) : List<Image>? = service.getProductImages(UUID.fromString(image_pid))

}