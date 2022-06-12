package pt.isel.WebApp.lib.services

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerResponse.async
import pt.isel.WebApp.lib.services.blockchain.ExchangeService
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeHolder
import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entity.*
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis


@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService



    private val exchangeService = ExchangeService("HTTP://127.0.0.1:7545")

    //Image
    fun addImage(image: Image) = dbService.addImage(image)

    fun getImages() = dbService.getImages()

    fun getImage(id: UUID) = dbService.getImage(id)


    fun getProductImages(id: UUID) = dbService.getProductImages(id)

    fun deleteImage(id: UUID) = dbService.deleteImage(id)


    //Moderator
    fun createModerator(mod: Moderator) = dbService.createModerator(mod)

    fun getModerators() = dbService.getModerators()

    fun getModerator(id: UUID) = dbService.getModerator(id)

    fun updateModerator(id: UUID, moderator: Moderator) = dbService.updateModerator(id,moderator)

    fun deleteModerator(id: UUID) = dbService.deleteModerator(id)

    //Product
    fun addProduct(product: Product) = dbService.createProduct(product)


    fun getProducts() = dbService.getProducts()

    fun getSellerProducts(id: UUID) = dbService.getSellerProducts(id)

    fun updateProduct(id: UUID, product: Product) = dbService.updateProduct(id,product)

    fun getProduct(id: UUID) = dbService.getProduct(id)

    fun deleteProduct(id: UUID) = dbService.deleteProduct(id)


    //Seller
    fun createSeller(seller: Seller) = dbService.createSeller(seller)

    fun getSellers() = dbService.getSellers()

    fun getSeller(id: UUID) = dbService.getSeller(id)

    fun deleteSeller(id: UUID) = dbService.deleteSeller(id)

    fun updateSeller(id: UUID, seller: Seller) = dbService.updateSeller(id,seller)


    //User
    suspend fun createUser(user: User) = coroutineScope {
        return@coroutineScope dbService.createUser(user)
    }

    suspend fun getUsers() = coroutineScope{
        dbService.getUsers()
    }

    fun getUser(id: UUID) = dbService.getUser(id)

    fun deleteUser(id: UUID) = dbService.deleteUser(id)
    fun updateUser(id: UUID, user: User) = dbService.updateUser(id,user)

    //Exchange
    // TODO: 07/06/2022 make it async
    suspend fun createExchange(client_id :UUID, productID : UUID, quantity: Int)   = coroutineScope{
        //getProduct -> price do produto
        /*val product = getProduct(productID).get() //todo    --> tranformar em async
        //get seller->Adrress from produto = getProductSeller
        val seller = getSeller(product.sid).get()  //todo   --> tranformar em async
        //criar Exchange
        val end_date=Date(Date().time + 2678400000)
        val exchange = Exchange(
            client_id,
            seller.id,
            productID,
            product.price,
            quantity,
            end_date
        )


        //inserir na block
        var transactionReceipt = exchangeService.newExchange(exchange.id.toString(),product.price * quantity , seller.wallet,Date().time)

        }
        return@coroutineScope ""*/
    }

    fun getExchanges() = dbService.getExchanges()

    fun getExchange(id: UUID) = dbService.getExchange(id)

    fun getUserExchanges(id: UUID) = dbService.getUserExchanges(id)

    fun completeExchange(id: UUID) = dbService.completeExchange(id)



}