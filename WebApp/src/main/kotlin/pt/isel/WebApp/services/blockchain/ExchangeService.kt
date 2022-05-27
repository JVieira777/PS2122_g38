package pt.isel.WebApp.services.blockchain

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.services.blockchain.utils.GasProvider
import pt.isel.WebApp.services.blockchain.wrappers.ExchangeHolder
import java.math.BigInteger
import java.util.*

private val CONTRACT_ADDRESS = ""
private val BLOCKCHAIN_NETWORK_URL : String = "HTTP://127.0.0.1:7545"
private val CREDENTIALS : Credentials = Credentials.create(System.getenv("private_key"))


private val web3j : Web3j = Web3j.build(HttpService(BLOCKCHAIN_NETWORK_URL))
private val gasProvider : GasProvider = setupGasProvider()

fun setupGasProvider() : GasProvider{
    var toret = GasProvider()
    gasProvider.addNewGasInfo("newExchange" , BigInteger("21000"), BigInteger("2"))
    gasProvider.addNewGasInfo("refund" , BigInteger("21000"), BigInteger("2"))
    gasProvider.addNewGasInfo("completeOrder" , BigInteger("21000"), BigInteger("2"))
    return toret
}

/*val exchangeHolder : ExchangeHolder = ExchangeHolder.load(
        CONTRACT_ADDRESS,
        web3j,
        CREDENTIALS,
        ExchangeHolder.GAS_PRICE,
        ExchangeHolder.GAS_LIMIT
)*/


val exchangeHolder : ExchangeHolder = ExchangeHolder.load(
                        CONTRACT_ADDRESS,
                        web3j,
                        CREDENTIALS,
                        gasProvider
)

fun newExchange(orderId : BigInteger, price: BigInteger, destinationAddress: String, end_date : Date) =
    exchangeHolder.newExchange(orderId,price,destinationAddress, end_date.time.toBigInteger()).send()



fun completeOrder( orderId : BigInteger) =
    exchangeHolder.completeOrder(orderId).send()



fun refund(orderId: BigInteger) =
    exchangeHolder.refund(orderId).send()



fun main(){
    //deploy contract and get address

}