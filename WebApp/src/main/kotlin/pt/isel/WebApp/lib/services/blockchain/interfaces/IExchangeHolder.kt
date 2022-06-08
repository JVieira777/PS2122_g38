package pt.isel.WebApp.lib.services.blockchain.interfaces

import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tuples.generated.Tuple6
import java.math.BigInteger
import java.util.concurrent.CompletableFuture

interface IExchangeHolder {

    suspend fun newExchange(orderId : BigInteger, price: BigInteger, destinationAddress: String, end_date : BigInteger) : CompletableFuture<TransactionReceipt>



    suspend fun completeExchange( orderId : BigInteger) : CompletableFuture<TransactionReceipt>



    suspend fun getExchange(orderId: BigInteger) :  CompletableFuture<Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>>

}