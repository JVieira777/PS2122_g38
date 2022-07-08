package pt.isel.WebApp.lib.Controllers.Api

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.Controllers.*
import pt.isel.WebApp.lib.services.Services


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/ExchangeManager")
class ExchangeManagerController {


    //@Autowired
    //private lateinit var exchangeManager: ExchangeManagerService
    @Autowired
    private lateinit var services: Services

    //add new exchange
    @PutMapping("new")
    fun newExchange(@RequestBody exp : ExchangeParams) = runBlocking{
        println("--------------------------------------" + exp.destination)
        try{
            return@runBlocking ResponseEntity(services.exchangeManager.newExchange(exp.value,exp.destination,exp.expiration_date.toString()),HttpStatus.OK)
        }catch (e : Exception){

        }
    }

    //get exchanges
    @GetMapping("exchange/{id}/info")
    fun getExchange(@PathVariable("id") ex_id: String) = runBlocking{
        try {
            withTimeout(GETS_TIMEOUTS){
                return@withTimeout ResponseEntity( services.exchangeManager.getExchange(ex_id).join(), HttpStatus.OK)
            }
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity("Something went wrong", HttpStatus.REQUEST_TIMEOUT)
        }
    }

    //request refund
    @PutMapping("exchange/{id}/refund")
    fun refundRequest(@PathVariable("id") ex_id: String, @RequestBody refundForm: RefundForm) = runBlocking {
        try{
            if(validadeReformRequest(refundForm)){
                return@runBlocking ResponseEntity(services.exchangeManager.refund(ex_id),HttpStatus.OK)
            }
            return@runBlocking  ResponseEntity("Request denied",HttpStatus.OK)
        }catch (e : Exception){

        }
    }

}



