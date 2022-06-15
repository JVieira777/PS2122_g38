package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

import java.math.BigInteger
internal class ExchangeServiceTest {

    var exchangeService : ExchangeService = ExchangeService("HTTP://127.0.0.1:7545","0x6bc05c4e2208d00902a6b846dd59bf0ddb998ca1")


    @Test
    fun loadContract() = runBlocking {
        val smartContract = async {  exchangeService.loadContract("0x6bc05c4e2208d00902a6b846dd59bf0ddb998ca1") }
        assert(smartContract.await().isValid)
    }

    @Test
    fun newExchange() = runBlocking{
        val addExchange =
            exchangeService.newExchange(
                "5",
                27,
                "0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32",
                System.currentTimeMillis().toString()
            )

        println( "status" + addExchange.status)

        assert(addExchange.isStatusOK)
    }

    @Test
    fun getExchange() = runBlocking{
        val exchange =
            exchangeService.getExchange("5")

        val result = exchange
        println(result.toString())
        assert(result.component1() ==BigInteger("27"))
        assert(result.component3() == "0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32".lowercase() )
    }

    @Test
    fun completeOrder() = runBlocking {

       val completeRequest=  exchangeService.completeExchange("2")



        val getExchange=  exchangeService.getExchange("2")


        val completeRequestResult = completeRequest
        val exchange = getExchange

        assert(completeRequestResult.isStatusOK)

        assert(exchange.component6())
    }

}