package pt.isel.WebApp.lib.Controllers



val GETS_TIMEOUTS : Long = 3000
val POST_TIMEOUTS : Long = 7000

data class ExchangeParams(val destination : String, val value: Long, val expiration_date: Long)
data class RefundForm(val reason : String)


fun validadeReformRequest(refundForm: RefundForm): Boolean{

    //enviar para mod e esperar resultado
    //return modEval(refundForm)
    if(refundForm.reason == "valid"){
        return true
    }
    return false
}