package pt.isel.WebApp.lib.Controllers


import org.springframework.stereotype.Component
import java.util.*


val GETS_TIMEOUTS : Long = 10000
val POST_TIMEOUTS : Long = 60000

data class ExchangeParams(val destination : String, val value: Long, val expiration_date: Long)
data class RefundForm(val reason : String)

data class ClientDTO(val name: String, val credential: CredentialDTO)

//@Component
data class CredentialDTO(val email: String, val password: String)

data class TokenDTO ( val code : UUID, val expiration : Date, val num_calls :Int, val status: String, val client_id : UUID)

fun validadeReformRequest(refundForm: RefundForm): Boolean{

    //enviar para mod e esperar resultado
    //return modEval(refundForm)

    if(refundForm.reason == "valid"){
        return true
    }
    return false
}