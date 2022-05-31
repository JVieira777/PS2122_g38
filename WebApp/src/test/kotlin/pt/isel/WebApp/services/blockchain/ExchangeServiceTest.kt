package pt.isel.WebApp.services.blockchain

import org.junit.jupiter.api.Test


import java.math.BigInteger


internal class ExchangeServiceTest {

    var exchangeService : ExchangeService = ExchangeService("HTTP://127.0.0.1:7545","0xcc007d1aac5d03d31fbebea67a158023f1fe10a8")

    @Test
    fun leadContract() {
        val smartContract = exchangeService.loadContract("0xcc007d1aac5d03d31fbebea67a158023f1fe10a8")
        assert(smartContract.isValid)
    }

    @Test
    fun newExchange() {
        val transaction = exchangeService.newExchange(BigInteger("1"), BigInteger("25"),"0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32",
            System.currentTimeMillis().toBigInteger()
        )
        assert(transaction.isStatusOK)
    }

    @Test
    fun getExchange(){
        val exchange  = exchangeService.getExchange(BigInteger("1"))
        println("exchange values: ${exchange.toString()}")
        println("price: " + exchange.component1())
        println("isCompleted: " + exchange.component6())
        assert(exchange.component1() == BigInteger("25"))
        assert(exchange.component3().equals(("0xa461422cB5dBD826C9031bF8A196E5C5AFAeDe32").lowercase()))
    }

    @Test
    fun completeOrder() {

        exchangeService.completeOrder(BigInteger("1"))
        assert(exchangeService.getExchange(BigInteger("1")).component6())
    }

    @Test
    fun pay() {

    }

    @Test
    fun refund() {

    }

    @Test
    fun collect() {

    }
}