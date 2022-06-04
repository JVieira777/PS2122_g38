package pt.isel.WebApp.Backend.services.blockchain

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.Backend.services.blockchain.interfaces.IExchangeHolder
import pt.isel.WebApp.Backend.services.blockchain.utils.GasProvider
import pt.isel.WebApp.Backend.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.Backend.services.blockchain.wrappers.ExchangeHolder
import java.math.BigInteger

class ExchangeService(blockchain_url : String, contract_address: String? = null) : IExchangeHolder {

    private val web3j : Web3j = Web3j.build(HttpService(blockchain_url))
    private val CREDENTIALS : Credentials = Credentials.create("293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f")
    private val gasProvider : GasProvider = setupGasProvider()

    private val exchangeHolder : ExchangeHolder =
       ExchangeHolder.load(contract_address?:deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)

    fun deployContract(gasProvider: GasProvider) =
        ExchangeHolder.deploy(web3j, CREDENTIALS, gasProvider).send().contractAddress

    fun loadContract(address: String?) : ExchangeHolder {
        return ExchangeHolder.load(address, web3j, CREDENTIALS, ExchangeHolder.GAS_PRICE, ExchangeHolder.GAS_LIMIT)
    }

    override fun newExchange(orderId: BigInteger, price: BigInteger, destinationAddress: String, end_date: BigInteger) : TransactionReceipt  {
       return exchangeHolder.newExchange(orderId,price,destinationAddress, end_date).send()
    }

    override fun getExchange(orderId: BigInteger) = exchangeHolder.exchanges(orderId).send()

    override fun completeOrder(orderId: BigInteger) : TransactionReceipt {
        return exchangeHolder.completeOrder(orderId).send()
    }

}