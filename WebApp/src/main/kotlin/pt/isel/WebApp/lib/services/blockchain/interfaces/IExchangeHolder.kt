package pt.isel.WebApp.lib.services.blockchain.interfaces

import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tuples.generated.Tuple6
import java.math.BigInteger

interface IExchangeHolder {

    fun newExchange(orderId : BigInteger, price: BigInteger, destinationAddress: String, end_date : BigInteger) : TransactionReceipt



    fun completeOrder( orderId : BigInteger) : TransactionReceipt



    fun getExchange(orderId: BigInteger) :  Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>

}