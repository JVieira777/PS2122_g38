package pt.isel.WebApp.lib.services


import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.isel.WebApp.lib.services.blockchain.ExchangeService
import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entity.*
import java.util.*



@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService



    //private val exchangeService = ExchangeService("HTTP://127.0.0.1:7545")
    private val exchangeService = ExchangeService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b","0x01cF80D38d8C7196cd9bc2651073d4728BE3D9e9")

    //Image
    suspend fun addImage(image: Image) = coroutineScope {
        dbService.addImage(image)
    }

    suspend fun getImages() = coroutineScope {
        dbService.getImages()
    }

    suspend fun getImage(id: UUID) = coroutineScope {
        dbService.getImage(id)
    }


    suspend fun getProductImages(id: UUID) = coroutineScope {
        dbService.getProductImages(id)
    }

    suspend fun deleteImage(id: UUID) = coroutineScope {
        dbService.deleteImage(id)
    }


    //Moderator
    suspend fun createModerator(mod: Moderator) = coroutineScope {
        dbService.createModerator(mod)
    }

    suspend fun getModerators() = coroutineScope {
        dbService.getModerators()
    }

    suspend fun getModerator(id: UUID) = coroutineScope {
        dbService.getModerator(id)
    }

    suspend fun updateModerator(id: UUID, moderator: Moderator) = coroutineScope {
        dbService.updateModerator(id,moderator)
    }

    suspend fun deleteModerator(id: UUID) = coroutineScope {
        dbService.deleteModerator(id)
    }

    //Product
    suspend fun addProduct(product: Product) = coroutineScope {
        dbService.createProduct(product)
    }


    suspend fun getProducts() = coroutineScope {
        dbService.getProducts()
    }

    suspend fun getSellerProducts(id: UUID) = coroutineScope {
        dbService.getSellerProducts(id)
    }

    suspend fun updateProduct(id: UUID, product: Product) = coroutineScope {
        dbService.updateProduct(id,product)
    }

    suspend fun getProduct(id: UUID) = coroutineScope {
        dbService.getProduct(id)
    }

    suspend fun deleteProduct(id: UUID) = coroutineScope {
        dbService.deleteProduct(id)
    }


    //Seller
    suspend fun createSeller(seller: Seller) = coroutineScope {
        dbService.createSeller(seller)
    }

    suspend fun getSellers() = coroutineScope {
        dbService.getSellers()
    }

    suspend fun getSeller(id: UUID) = coroutineScope {
        dbService.getSeller(id)
    }

    suspend fun deleteSeller(id: UUID) = coroutineScope {
        dbService.deleteSeller(id)
    }

    suspend fun updateSeller(id: UUID, seller: Seller) = coroutineScope {
        dbService.updateSeller(id,seller)
    }


    //User
    suspend fun createUser(user: User) = coroutineScope {
        return@coroutineScope dbService.createUser(user)
    }

    suspend fun getUsers() = coroutineScope{
        dbService.getUsers()
    }

    suspend fun getUser(id: UUID) = coroutineScope {
        dbService.getUser(id)
    }

    suspend fun deleteUser(id: UUID) = coroutineScope {
        dbService.deleteUser(id)
    }
    suspend fun updateUser(id: UUID, user: User) = coroutineScope {
        dbService.updateUser(id,user)
    }

    //Exchange
    // TODO: 07/06/2022
    suspend fun createExchange(client_id :UUID, productID : UUID, quantity: Int) : Pair<Boolean, String>  = coroutineScope{
        //getProduct -> price do produto
        val product = async{ getProduct(productID)}.await()
        //get seller->Adrress from produto = getProductSeller
        val seller = async{ getSeller(product.second.sid) }.await()
        //criar Exchange
        val end_date=Date(Date().time + 2678400000)
        val exchange = Exchange(
            client_id,
            seller.second.id,
            productID,
            product.second.price,
            quantity,
            end_date
        )

        val transactionReceipt = exchangeService.newExchange(exchange.id.toString(),(product.second.price * quantity).toLong() , seller.second.wallet,Date().time.toString()).join()
        if(transactionReceipt.status == "0x1"){
            return@coroutineScope dbService.createExchange(exchange)
        }
        return@coroutineScope Pair(false, "failed to create")
    }

    suspend fun getExchanges() = coroutineScope {
       dbService.getExchanges()
    }

    suspend fun getExchange(id: UUID) = coroutineScope {
        dbService.getExchange(id)
    }

    suspend fun getUserExchanges(id: UUID) = coroutineScope {
        dbService.getUserExchanges(id)
    }

    suspend fun completeExchange(id: UUID) : Pair<Boolean,String> = coroutineScope {
        val exchange = exchangeService.getExchange(id.toString()).join()

        if(exchange.component5()){ //if is payed
            val result = exchangeService.completeExchange(id.toString()).join()
            if(result.status == "0x1"){
                val dbresult = async { dbService.completeExchange(id)}.await()
                if(dbresult.first){
                    return@coroutineScope Pair(true,"Successfully completed Exchange: $id")
                }
            }
            return@coroutineScope Pair(false,"failed to complete Exchange: $id")
        }
       return@coroutineScope Pair(false,"failed")
    }

}