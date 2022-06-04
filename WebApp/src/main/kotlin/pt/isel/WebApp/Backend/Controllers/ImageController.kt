package pt.isel.WebApp.Backend.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.Backend.Entity.Image
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.WebApp.Backend.services.Services
import java.util.*

@RestController
@RequestMapping("/image")
class ImageController () {


    @Autowired
    private lateinit var service : Services

    @GetMapping("/{iid}")
    fun getImage(@PathVariable("iid") image_id: String) : Optional<Image> = service.getImage(UUID.fromString(image_id))

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
    fun getImages() : List<Image>? = service.getImages()

    @DeleteMapping("/{iid}")
    fun deleteImage(@PathVariable("iid") image_id: String) : ResponseEntity<String> {
        val status = service.deleteImage(UUID.fromString(image_id))
        return if (status.equals("Success")) {
            ResponseEntity(status, HttpStatus.OK)
        } else {
            ResponseEntity(status, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{pid}/images")
    fun getProductImages(@PathVariable("pid") image_pid: String) : List<Image>? = service.getProductImages(UUID.fromString(image_pid))

}