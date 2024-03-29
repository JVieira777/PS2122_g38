package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.Image
import java.util.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/image")
class ImageController {


    @Autowired
    private lateinit var services: Services

    @GetMapping("/{iid}")
    fun GetImage(@PathVariable("iid") image_id: String): ResponseEntity<Image> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS) {
                return@withTimeout ResponseEntity(services.getImage(UUID.fromString(image_id)).second, HttpStatus.OK)
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PostMapping
    fun createImage(@RequestBody image: Image): ResponseEntity<String> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val response = services.addImage(image)
                return@withTimeout if (response.first) {
                    ResponseEntity(response.second, HttpStatus.OK)
                } else {
                    ResponseEntity("Failed to add Image", HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity("Failed to add Image, Try again later", HttpStatus.REQUEST_TIMEOUT)
        }

    }

    @GetMapping
    fun GetImages(): ResponseEntity<List<Image>?> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS) {
                return@withTimeout ResponseEntity(services.getImages().second, HttpStatus.OK)
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @DeleteMapping("/{iid}")
    fun DeleteImage(@PathVariable("iid") image_id: String): ResponseEntity<String> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.deleteImage(UUID.fromString(image_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity("Unable to delete image, try again later", HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping("/{pid}")
    fun GetAllImagesFromAProduct(@PathVariable("pid") image_pid: String): ResponseEntity<Image?> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS) {
                return@withTimeout ResponseEntity(
                    services.getProductImages(UUID.fromString(image_pid)).second.first(),
                    HttpStatus.OK
                )
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

}