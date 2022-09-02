package pt.isel.WebApp.lib.Controllers.Api

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.Controllers.ExchangeParams
import pt.isel.WebApp.lib.midlewares.annotations.AllowAnnonymous
import pt.isel.WebApp.lib.midlewares.annotations.ValidateToken
import pt.isel.WebApp.lib.services.Services
import javax.servlet.http.HttpServletRequest


//@CrossOrigin(origins = ["http://localhost:3000"],maxAge = 3600)
@RestController
@RequestMapping("/ExchangeManager")
class ExchangeManagerController {


    //@Autowired
    //private lateinit var exchangeManager: ExchangeManagerService
    @Autowired
    private lateinit var services: Services

    //add new exchange
    @AllowAnnonymous
    @ValidateToken
    @PutMapping("new")
    fun newExchange(@RequestBody exp : ExchangeParams, request : HttpServletRequest) = runBlocking{
        /*if(!(request.getAttribute("tokenIsValid") as Boolean)){
            return@runBlocking ResponseEntity("Invalid Token", HttpStatus.OK)
        }*/
        try {
            val response = services.exchangeManager.newExchange(exp.value,exp.destination,exp.expiration_date.toString())

            return@runBlocking ResponseEntity(response,HttpStatus.OK)
        }catch (e : Exception){}

        return@runBlocking ResponseEntity("Failed to create exchange",HttpStatus.OK)

    }

    //get exchanges
    @AllowAnnonymous
    @ValidateToken
    @GetMapping("exchange/{id}/info")
    fun getExchange(@PathVariable("id") ex_id: String, request : HttpServletRequest) = runBlocking{
        /*if(!(request.getAttribute("tokenIsValid") as Boolean)){
            return@runBlocking ResponseEntity("Invalid Token", HttpStatus.OK)
        }*/
        try {
            /*withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity( services.exchangeManager.getExchange(ex_id), HttpStatus.OK)
            }*/
            val exchangeDto = services.exchangeManager.getExchange(ex_id) ?: return@runBlocking ResponseEntity("{}",HttpStatus.OK)
            return@runBlocking ResponseEntity( exchangeDto , HttpStatus.OK)
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity("Something went wrong", HttpStatus.OK)
        }
    }

    //request refund
    @AllowAnnonymous
    @ValidateToken
    @PutMapping("exchange/{id}/refund")
    //fun refundRequest(@PathVariable("id") ex_id: String, @RequestBody refundForm: RefundForm, request : HttpServletRequest) = runBlocking {
    fun refundRequest(@PathVariable("id") ex_id: String, request : HttpServletRequest) = runBlocking {

        /*if(!(request.getAttribute("tokenIsValid") as Boolean)){
            return@runBlocking ResponseEntity("Invalid Token", HttpStatus.OK)
        }*/
        try{
            //if(validadeReformRequest(refundForm)){
                return@runBlocking ResponseEntity(services.exchangeManager.refund(ex_id),HttpStatus.OK)
            //}
            return@runBlocking  ResponseEntity("Request denied",HttpStatus.OK)
        }catch (e : Exception){
            println(e)
            return@runBlocking ResponseEntity("Something went wrong", HttpStatus.REQUEST_TIMEOUT)
        }
    }
}



