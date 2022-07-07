package pt.isel.WebApp.lib.Controllers.Api

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.Controllers.POST_TIMEOUTS
import pt.isel.WebApp.lib.services.blockchain.ExchangeManagerService


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/ExchangeManager")
class ExchangeManagerController {


    @Autowired
    private lateinit var exchangeManager: ExchangeManagerService

    //add new exchange
    @PutMapping("new")
    fun newExchange(@RequestBody exp :ExchangeParams)= runBlocking{
        try{
            return@runBlocking ResponseEntity(exchangeManager.newExchange(exp.value,exp.destination,exp.expiration_date.toString()),HttpStatus.OK)
        }catch (e : Exception){

        }
    }

    //get exchanges
    @GetMapping("info/:id")
    fun getExchange(@PathVariable("id") ex_id: String) = runBlocking{
        try {
            withTimeout(POST_TIMEOUTS){
                return@withTimeout ResponseEntity( exchangeManager.getExchange(ex_id).join(), HttpStatus.OK)
            }
        }catch (e : TimeoutCancellationException){
            return@runBlocking ResponseEntity("Something went wrong", HttpStatus.REQUEST_TIMEOUT)
        }
    }

    //request refund
    @PutMapping("info/:id/refund")
    fun refundRequest(@PathVariable("id") ex_id: String, @RequestBody refundForm: RefundForm) = runBlocking {
        try{
            if(validadeReformRequest(refundForm)){
                return@runBlocking ResponseEntity(exchangeManager.refund(ex_id),HttpStatus.OK)
            }
            return@runBlocking  ResponseEntity("Request denied",HttpStatus.OK)
        }catch (e : Exception){

        }
    }
}

fun validadeReformRequest(refundForm: RefundForm): Boolean{
    if(refundForm.reason == "valid"){
        return true
    }
    return false
}

data class ExchangeParams(val destination : String, val value: Long, val expiration_date: Long)
data class RefundForm(val reason : String)

