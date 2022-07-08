package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import org.web3j.tuples.generated.Tuple7
import pt.isel.WebApp.lib.services.blockchain.utils.GasProvider
import pt.isel.WebApp.lib.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeManager
import java.math.BigInteger
import java.util.concurrent.CompletableFuture


class ExchangeManagerService(blockchain_url : String, contract_address: String? = "0xb151471B6A5AEFbd6d6FB7ACa5858307d9fA2383") {



    private val web3j: Web3j = Web3j.build(HttpService(blockchain_url))

    private val CREDENTIALS: Credentials = Credentials.create("0xfdcba3185e2eae5283bb2eab190b5007a946358da89ee07025c924fc0e95b5d6") //kovan testnet key
    //private val CREDENTIALS: Credentials = Credentials.create(System.getenv("private_key")) //kovan testnet key

    private val gasProvider: GasProvider = setupGasProvider()



    private val exchangeManager: ExchangeManager =ExchangeManager.load(contract_address ?: deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)
    //kovan contract address = 0xb151471B6A5AEFbd6d6FB7ACa5858307d9fA2383
    //private val exchangeManager : ExchangeManager = ExchangeManager.load("0x3593CbEC414E1f96dBd7769Db1237E3E97b06C15",web3j,CREDENTIALS,gasProvider)

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
        return@coroutineScope exchangeManager.refund(BigInteger(orderId)).sendAsync()
    }

    suspend fun getExchange(orderId: String): CompletableFuture<Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>> = coroutineScope{
        return@coroutineScope exchangeManager.exchanges(BigInteger(orderId)).sendAsync()
    }
}

