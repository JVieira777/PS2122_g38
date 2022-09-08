package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.RefundForm
import java.util.*


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/RefundRequest")
class RefundRequestController {

    @Autowired
    private lateinit var services: Services


    @PostMapping
    fun createRefundRequest(@RequestBody req: RefundForm): ResponseEntity<String> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                return@withTimeout ResponseEntity(services.createRefundRequest(req).second, HttpStatus.OK)
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(services.createRefundRequest(req).second, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping("/AllRequests")
    fun GetRefundRequests(): ResponseEntity<List<RefundForm>?> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.getRefundRequests()
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping("/{rid}")
    fun GetRefundRequestbyID(@PathVariable("rid") request_id: String): ResponseEntity<RefundForm> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.getRefundRequestbyID(UUID.fromString(request_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }

    @GetMapping
    fun GetRefundRequest(): ResponseEntity<RefundForm?> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.getRefundRequest()
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(null, HttpStatus.REQUEST_TIMEOUT)
        }
    }


    @DeleteMapping("/{rid}")
    fun DeleteRefundRequest(@PathVariable("rid") request_id: String): ResponseEntity<String> = runBlocking {
        try {
            withTimeout(POST_TIMEOUTS) {
                val status = services.deleteRefundRequest(UUID.fromString(request_id))
                return@withTimeout if (status.first) {
                    ResponseEntity(status.second, HttpStatus.OK)
                } else {
                    ResponseEntity(status.second, HttpStatus.BAD_REQUEST)
                }
            }
        } catch (e: TimeoutCancellationException) {
            return@runBlocking ResponseEntity(
                "Unable to delete the request, try again later",
                HttpStatus.REQUEST_TIMEOUT
            )
        }
    }
}