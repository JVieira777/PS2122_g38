package pt.isel.WebApp.services.blockchain.utils

import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

class GasProvider : ContractGasProvider {

    var functionsPrice : HashMap<String,GasInfo> = HashMap<String,GasInfo>()


    fun addNewGasInfo(contractFunc: String, gasPrice: BigInteger?, gasLimit: BigInteger?) =
        functionsPrice.put(contractFunc,GasInfo(gasPrice,gasLimit))

    override fun getGasPrice(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasPrice
    }

    override fun getGasPrice(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun getGasLimit(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasLimit
    }

    override fun getGasLimit(): BigInteger {
        TODO("Not yet implemented")
    }
}

data class GasInfo(val gasPrice: BigInteger?, var gasLimit: BigInteger?)