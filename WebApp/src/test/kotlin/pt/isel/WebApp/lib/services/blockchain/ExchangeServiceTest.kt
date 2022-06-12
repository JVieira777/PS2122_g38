package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.web3j.protocol.core.methods.response.TransactionReceipt
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
        val addExchange = async {
            exchangeService.newExchange(
                BigInteger("2"),
                BigInteger("25"),
                "0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32",
                System.currentTimeMillis().toBigInteger()
            )
        }
        val result = addExchange.await().get()

        assert(result.isStatusOK)
    }

    @Test
    fun getExchange() = runBlocking{
        val exchange = async {
            exchangeService.getExchange(BigInteger("2"))
        }
        val result = exchange.await().get()
        assert(result.component3() == "0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32".lowercase() )
    }

    @Test
    fun completeOrder() = runBlocking {
        val completeRequest = async {
            exchangeService.completeExchange(BigInteger("2"))
        }

        val getExchange= async {
            exchangeService.getExchange(BigInteger("2"))
        }

        val completeRequestResult = completeRequest.await().get()
        val exchange = getExchange.await().get()

        assert(completeRequestResult.isStatusOK)

        assert(exchange.component6())
    }

}