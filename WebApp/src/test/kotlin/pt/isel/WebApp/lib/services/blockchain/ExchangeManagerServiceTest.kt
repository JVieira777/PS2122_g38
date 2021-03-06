package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger


internal class ExchangeManagerServiceTest {

    val contractAddress = "0x3593CbEC414E1f96dBd7769Db1237E3E97b06C15"
    val exchangeService : ExchangeManagerService = ExchangeManagerService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b",contractAddress)
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
        assert(exchange.component1() == result.price)
        assert(exchange.component3().toString() == result.destination)
    }
}