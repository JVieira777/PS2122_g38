package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.*

import org.junit.jupiter.api.Test


import java.math.BigInteger


internal class ExchangeServiceTest {

    //var exchangeService : ExchangeService = ExchangeService("HTTP://127.0.0.1:7545","0x6bc05c4e2208d00902a6b846dd59bf0ddb998ca1")
    //remix contract addres = 0x0FdA6dE11D675DAf0bA221bD09fF6FF3DF82Cc26
    val contractAddress = "0x01cF80D38d8C7196cd9bc2651073d4728BE3D9e9"
    val exchangeService : ExchangeService = ExchangeService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b",contractAddress)
    val MONT_MILLIS = 2629800000
    @Test
    fun loadContract() = runBlocking {
        val smartContract = exchangeService.loadContract(contractAddress)
        println("contract in address : ${smartContract.contractAddress} is valid: ${smartContract.isValid}")
        assert(smartContract.isValid)
    }

    @Test
    fun newExchange() = runBlocking{
        val result =
            exchangeService.newExchange(
                "5",
                15,
                "0x0000000000000000000000000000000000000001",
                (System.currentTimeMillis()+ MONT_MILLIS).toString()
            ).join()

        println("result->$result")
        assert(result.isStatusOK)
        assert(contractAddress.lowercase() == result.to.lowercase())
    }

    @Test
    fun getExchange() = runBlocking{
        val def_exchange =exchangeService.getExchange("5").join()
        assert(def_exchange.component1() ==BigInteger("15"))
        println(def_exchange)
        assert(def_exchange.component3().lowercase() == "0x0000000000000000000000000000000000000001".lowercase() )
    }

    @Test
    fun completeOrder() = runBlocking {

        val completeRequest=  exchangeService.completeExchange("2").join()
        assert(completeRequest.isStatusOK)
        /*val getExchange=  exchangeService.getExchange("2")


        val completeRequestResult = completeRequest
        val exchange = getExchange

        assert(completeRequestResult.isStatusOK)

        assert(exchange.component6())*/
    }

}