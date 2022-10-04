package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.coroutineScope
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.lib.services.blockchain.utils.GasProvider
import pt.isel.WebApp.lib.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeManager
import java.math.BigInteger
import java.util.concurrent.CompletableFuture


class ExchangeManagerService(blockchain_url : String, contract_address: String? = "0xb151471B6A5AEFbd6d6FB7ACa5858307d9fA2383") {



    private val web3j: Web3j = Web3j.build(HttpService(blockchain_url))

    private val CREDENTIALS: Credentials = Credentials.create("0xfdcba3185e2eae5283bb2eab190b5007a946358da89ee07025c924fc0e95b5d6")



    private val gasProvider: GasProvider = setupGasProvider()



    private val exchangeManager: ExchangeManager =ExchangeManager.load(contract_address ?: deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)


    fun deployContract(gasProvider: GasProvider) =
        ExchangeManager.deploy(web3j, CREDENTIALS, gasProvider).send().contractAddress

    suspend fun newExchange(price: Long, destinationAddress: String, end_date: String ) = coroutineScope{
        val receipt = exchangeManager.newExchange( BigInteger(price.toString()), destinationAddress, BigInteger(end_date)).send()
        if(receipt.isStatusOK){
            return@coroutineScope exchangeManager.getExchangeCreationEvents(receipt).first()
        }
        return@coroutineScope ExchangeManager.ExchangeCreationEventResponse()
    }

    suspend fun refund(orderId: String): CompletableFuture<TransactionReceipt> = coroutineScope{
        val x = exchangeManager.refund(BigInteger(orderId)).sendAsync()
        println(x)
        return@coroutineScope x
        return@coroutineScope exchangeManager.refund(BigInteger(orderId)).sendAsync()
    }


    suspend fun getExchange(orderId: String): ExchangeDto? = coroutineScope{
        val exchangeTuple = exchangeManager.exchanges(BigInteger(orderId)).send()
        val exchangeDto = ExchangeDto(exchangeTuple.component1(),exchangeTuple.component2(),exchangeTuple.component3(),exchangeTuple.component4(),exchangeTuple.component5(),exchangeTuple.component6(), exchangeTuple.component7())
        return@coroutineScope exchangeDto
    }
}

data class ExchangeDto(val price: BigInteger, val buyerAddress: String, val sellerAddress: String, val end_date: BigInteger, val payed : Boolean, val refundable: Boolean, val completed: Boolean)