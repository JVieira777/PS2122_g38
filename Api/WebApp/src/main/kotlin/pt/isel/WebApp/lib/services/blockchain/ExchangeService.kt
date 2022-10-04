package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.*
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.lib.services.blockchain.interfaces.IExchangeHolder
import pt.isel.WebApp.lib.services.blockchain.utils.GasProvider
import pt.isel.WebApp.lib.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeHolder
import java.math.BigInteger
import java.util.concurrent.CompletableFuture

class ExchangeService(blockchain_url : String, contract_address: String? = null) : IExchangeHolder {

    private val web3j : Web3j = Web3j.build(HttpService(blockchain_url))


    private val CREDENTIALS : Credentials = Credentials.create("0xfdcba3185e2eae5283bb2eab190b5007a946358da89ee07025c924fc0e95b5d6")
    private val gasProvider : GasProvider = setupGasProvider()

    private val exchangeHolder : ExchangeHolder =
       ExchangeHolder.load(contract_address?:deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)

    fun deployContract(gasProvider: GasProvider) =
        ExchangeHolder.deploy(web3j, CREDENTIALS, gasProvider).send().contractAddress

    fun loadContract(address: String?) : ExchangeHolder {
        return ExchangeHolder.load(address, web3j, CREDENTIALS, ExchangeHolder.GAS_PRICE, ExchangeHolder.GAS_LIMIT)
    }

    override suspend fun newExchange(orderId: String, price: Long, destinationAddress: String, end_date: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.newExchange(BigInteger(orderId),BigInteger(price.toString()) ,destinationAddress,BigInteger(end_date)).sendAsync()
    }

    override suspend fun getExchange(orderId: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.exchanges(BigInteger(orderId)).sendAsync()
    }

    override suspend fun completeExchange(orderId: String) = coroutineScope {
        val ex = getExchange(orderId).join()
        if(ex.component4() <= System.currentTimeMillis().toBigInteger()){
            return@coroutineScope exchangeHolder.completeOrder(BigInteger(orderId)).sendAsync()
        }
        return@coroutineScope CompletableFuture<TransactionReceipt>()
    }


}
