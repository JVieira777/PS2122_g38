package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.database.Entity.Image
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.WebApp.lib.services.Services
import java.sql.Time
import java.util.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/image")
class ImageController () {


    @Autowired
    private lateinit var service : Services

    @GetMapping("/{iid}")
    fun GetImage(@PathVariable("iid") image_id: String) : ResponseEntity<Image> = runBlocking{
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity(service.getImage(UUID.fromString(image_id)).second,HttpStatus.OK)
            }
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity(null,HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @PostMapping
    fun createImage(@RequestBody image : Image): ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val response = service.addImage(image)
                return@withTimeout if (response.first) {
                    ResponseEntity(response.second, HttpStatus.OK)
                } else {
                    ResponseEntity("Failed to add Image", HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity("Failed to add Image, Try again later", HttpStatus.REQUEST_TIMEOUT)
        }

    }

    @GetMapping
    fun GetImages() : ResponseEntity<List<Image>?> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity(service.getImages().second, HttpStatus.OK)
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity(null,HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @DeleteMapping("/{iid}")
    fun DeleteImage(@PathVariable("iid") image_id: String) : ResponseEntity<String> = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                val status = service.deleteImage(UUID.fromString(image_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        }catch (e: TimeoutCancellationException){
            return@runBlocking ResponseEntity("Unable to delete image, try again later", HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping("/{pid}")
    fun GetAllImagesFromAProduct(@PathVariable("pid") image_pid: String) : ResponseEntity<List<Image>?> = runBlocking {
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity(service.getProductImages(UUID.fromString(image_pid)).second,HttpStatus.OK)
            }
        }catch (e: TimeoutCancellationException ){
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

}