package pt.isel.WebApp.services.blockchain

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.services.blockchain.wrappers.ExchangeHolder

import java.math.BigInteger
import java.util.*

/**
 *return javascript to interact with metamask on client side
 */

private val BLOCKCHAIN_NETWORK_URL = "HTTP://127.0.0.1:7545"

private val web3j: Web3j = Web3j.build(HttpService(BLOCKCHAIN_NETWORK_URL))

//val CREDENTIALS = Credentials.create(System.getenv().get("private_key"))
private val CREDENTIALS = Credentials.create("293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f") //mock private Key

//val CONTRACT_ADDRESS = "".-
//val exchangeContract = loadContract(CONTRACT_ADDRESS)

fun deployContract() = ExchangeHolder.deploy(
    web3j,
    CREDENTIALS,
    ExchangeHolder.GAS_PRICE,
    ExchangeHolder.GAS_LIMIT
).send().contractAddress


fun loadContract(address: String?) = ExchangeHolder
    .load(
        address,
        web3j,
        CREDENTIALS,
        ExchangeHolder.GAS_PRICE,
        ExchangeHolder.GAS_LIMIT
    )
fun addNewExchange(contractAddress : String, orderId : BigInteger, price: BigInteger, destinationAddress: String, end_date : Date) =
    loadContract(contractAddress).newExchange(orderId,price,destinationAddress, end_date.time.toBigInteger()).send()


fun getExchange(contractAddress: String,orderId: BigInteger) =
    loadContract(contractAddress).exchanges(orderId).send()

//fun refund(orderId: BigInteger) = ExchangeHolder.load(ADDRESS)*/

fun main(){
    val address = deployContract()
    print(address)
}

///C:\Users\Joao_\.web3j\web3j-cli-shadow-1.4.1\lib
