package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger


internal class ExchangeManagerServiceTest {

    val contractAddress = "0x0FdA6dE11D675DAf0bA221bD09fF6FF3DF82Cc26"
    val exchangeService : ExchangeManagerService = ExchangeManagerService("https://goerli.infura.io/v3/18e3b06cbd1a4f4dae9d53af8e438bc9",contractAddress)
    val MONT_MILLIS = 2629800000


    @Test
    fun newExchange() = runBlocking{
        val result = exchangeService.newExchange(25,"0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590", "999999999")
        println("price: " + result.price.toString())
        println("destination: " + result.destination)
        assert(result.price == BigInteger("25"))
        assert(result.destination.lowercase() == "0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590".lowercase())
    }

    @Test
    fun refund() {
    }

    @Test
    fun getExchange() = runBlocking{
        val result = exchangeService.newExchange(50,"0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590", "111111111")
        val exchange = exchangeService.getExchange(result.exchange_id.toString())
        println("getExchange created exchange with id :" +result.exchange_id )
        //assert(exchange.component1() == result.price)
        //assert(exchange.component3().toString() == result.destination)
    }
}