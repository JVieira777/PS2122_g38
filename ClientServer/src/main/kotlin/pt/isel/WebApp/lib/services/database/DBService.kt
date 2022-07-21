package pt.isel.WebApp.lib.services.database

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.services.database.Entity.*
import pt.isel.WebApp.lib.services.database.Repository.*

import java.util.UUID
import javax.persistence.EntityNotFoundException


@Service
class DBService {

    @Autowired
  lateinit var imageRepository: ImageRepository
    @Autowired
  lateinit var moderatorRepository: ModeratorRepository
    @Autowired
   lateinit var productRepository: ProductRepository
    @Autowired
   lateinit var sellerRepository: SellerRepository
    @Autowired
   lateinit var userRepository: UserRepository
    @Autowired
    lateinit var exchangeRepository: ExchangeRepository




    //Image
    suspend fun addImage(image : Image) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            imageRepository.save(image)
            return@coroutineScope Pair(true,"Image added successfully")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Image Exception: ${e.message}")
        }
    }

    suspend fun getImages() :Pair<Boolean, List<Image>> = coroutineScope {
        return@coroutineScope Pair(true,imageRepository.findAll())
    }

    suspend fun getImage(id: UUID) :  Pair<Boolean, Image> = coroutineScope {
        return@coroutineScope Pair(
            true,
            imageRepository.findById(id).orElseThrow {
                    EntityNotFoundException()
            }
        )
    }


    suspend fun getProductImages(id: UUID) : Pair<Boolean, List<Image>> = coroutineScope {
        return@coroutineScope Pair(true,imageRepository.findAllImagesFromProduct(id))
    }

    suspend fun deleteImage(id: UUID) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            imageRepository.deleteById(id)
            return@coroutineScope Pair(true,"image was successfully removed")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Image Exception: ${e.message}")
        }
    }


    //Moderator
    suspend fun createModerator(mod : Moderator) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            moderatorRepository.save(mod)
            return@coroutineScope Pair(true,"Moderator added successfully")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Moderator Exception: ${e.message}")
        }
    }

    suspend fun getModerators() :Pair<Boolean, List<Moderator>> = coroutineScope {
        return@coroutineScope Pair(true,moderatorRepository.findAll())
    }

    suspend fun getModerator(id: UUID) : Pair<Boolean, Moderator> = coroutineScope {
        return@coroutineScope Pair(true,moderatorRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }

    suspend fun updateModerator(id: UUID, new_mod: Moderator) : Pair<Boolean,String> = coroutineScope {
        return@coroutineScope try {
            val moderator = moderatorRepository.findById(id).orElseThrow { EntityNotFoundException()}
            moderator.apply {
                this.description = new_mod.description
            }
            moderatorRepository.save(moderator)
            return@coroutineScope Pair(true,"Moderator successfully Updated")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Moderator Exception: ${e.message}")
        }
    }

    suspend fun deleteModerator(id: UUID) : Pair<Boolean,String> = coroutineScope {
        return@coroutineScope try {
            val mod = moderatorRepository.findById(id).orElseThrow { EntityNotFoundException()}
            mod.apply {
                this.terminated=true
            }
            moderatorRepository.save(mod)
            return@coroutineScope Pair(true,"moderator was successfully deleted")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Moderator Exception: ${e.message}")
        }
    }

    //Product
    suspend fun createProduct(product: Product) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            productRepository.save(product)
            return@coroutineScope Pair(true,"product was successfully created")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Product Exception: ${e.message}")
        }
    }

    suspend fun getProducts() :Pair<Boolean, List<Product>> = coroutineScope {
        return@coroutineScope Pair(true,productRepository.findAll())
    }


    suspend fun getProduct(id: UUID) : Pair<Boolean, Product> = coroutineScope {
        return@coroutineScope Pair(true,productRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }


    suspend fun getSellerProducts(id : UUID) : Pair<Boolean, List<Product>> = coroutineScope {
        return@coroutineScope Pair(true,productRepository.findAllProductsFromSeller(id))
    }

    suspend fun getProductsByName(name : String) : Pair<Boolean, List<Product>> = coroutineScope {
        return@coroutineScope Pair(true,productRepository.findProductsByName(name))
    }

    suspend fun updateProduct(id: UUID, product: Product): Pair<Boolean,String> = coroutineScope {
        return@coroutineScope try {
            val prod = productRepository.findById(id).orElseThrow { EntityNotFoundException()}
            prod.apply {
                this.name = product.name
                this.description = product.description
                this.price = product.price
                this.rate = product.rate
            }
            productRepository.save(prod)
            return@coroutineScope Pair(true,"product was successfully updated")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Product Exception: ${e.message}")
        }

    }

    suspend fun deleteProduct(id: UUID) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            productRepository.deleteById(id)
            return@coroutineScope Pair(true,"product was successfully removed")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Product Exception: ${e.message}")
        }
    }

    // TODO: 03/06/2022 é necessario esta funcionalidade
    //fun getUserProducts(id: UUID): java.util.Optional<Product> = //productRepository.findBy

    //Seller
    suspend fun createSeller(seller : Seller) : Pair<Boolean,String> = coroutineScope{
        return@coroutineScope try {
            sellerRepository.save(seller)
            return@coroutineScope Pair(true,"seller was successfully created")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Seller Exception: ${e.message}")
        }
    }

    suspend fun getSellers() : Pair<Boolean, List<Seller>> = coroutineScope {

        return@coroutineScope Pair(true, sellerRepository.findAll())
    }

    suspend fun getSeller(id: UUID) : Pair<Boolean, Seller> = coroutineScope {
        return@coroutineScope Pair(true,sellerRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }

    suspend fun updateSeller(id: UUID, newseller: Seller) : Pair<Boolean, String> = coroutineScope{
        return@coroutineScope try {
            val seller = sellerRepository.findById(id).orElseThrow { EntityNotFoundException()}
            seller.apply {
                this.rate = newseller.rate
                this.contry = newseller.contry
                this.description = newseller.description
            }
            sellerRepository.save(seller)
            return@coroutineScope Pair(true, "seller was successfully updated")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Seller Exception: ${e.message}")
        }
    }

    suspend fun deleteSeller(id: UUID) : Pair<Boolean, String> = coroutineScope{
        return@coroutineScope try {
            val seller = sellerRepository.findById(id).orElseThrow { EntityNotFoundException()}
            seller.apply {
                this.terminated=true
            }
            sellerRepository.save(seller)
            return@coroutineScope Pair(true,"seller was successfully deleted")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Seller Exception: ${e.message}")
        }
    }



    //User
    suspend fun createUser(user : User) : Pair<Boolean,String> = coroutineScope{
        delay(1000)
        return@coroutineScope try {
            userRepository.save(user)
            return@coroutineScope Pair(true,"user was successfully created")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"User Exception: ${e.message}")
        }
    }

    suspend fun getUsers() : Pair<Boolean, List<User>> = coroutineScope {

        return@coroutineScope Pair(true, userRepository.findAll())
    }

    suspend fun getUser(id: UUID) : Pair<Boolean, User> = coroutineScope {
        return@coroutineScope Pair(true,userRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }

    suspend fun deleteUser(id: UUID) : Pair<Boolean, String> = coroutineScope{
        return@coroutineScope try {
            val user = userRepository.findById(id).orElseThrow { EntityNotFoundException() }
            user.apply {
                this.terminated=true
            }
            userRepository.save(user)
            return@coroutineScope Pair(true,"user was successfully deleted")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"User Exception: ${e.message}")
        }
    }
    suspend fun updateUser(id: UUID, new_user: User) : Pair<Boolean, String> = coroutineScope{
            return@coroutineScope try {
                val user = userRepository.findById(id).orElseThrow { EntityNotFoundException() }
                user.apply {
                    this.rate = new_user.rate
                    this.emailAddress = new_user.emailAddress
                    this.password = new_user.password
                    this.profilePicture = new_user.profilePicture
                }
                userRepository.save(user)
                return@coroutineScope Pair(true, "user was successfully updated")
            } catch (e: Exception) {
                e.printStackTrace()
                return@coroutineScope Pair(false, "User Exception: ${e.message}")
            }
        }


    //Exchange
    suspend fun createExchange(exchange: Exchange) : Pair<Boolean, String> = coroutineScope{
        return@coroutineScope try {
            exchangeRepository.save(exchange)
            return@coroutineScope Pair(true, "exchanged was successfully created")
        }catch (e : Exception){
            e.printStackTrace()
            return@coroutineScope Pair(false,"Exchange Exception: ${e.message}")
        }
    }

    suspend fun getExchanges() : Pair<Boolean, List<Exchange>> = coroutineScope{
        return@coroutineScope Pair(true,exchangeRepository.findAll())
    }

    suspend fun getExchange(id: Long) : Pair<Boolean,Exchange> = coroutineScope{
        return@coroutineScope Pair(true,exchangeRepository.findById(id).orElseThrow { EntityNotFoundException() })
    }


    suspend fun getUserExchanges(id: UUID) : Pair<Boolean, List<Exchange>> = coroutineScope{
        return@coroutineScope Pair(true,exchangeRepository.GetAllUserExchanges(id))
    }

     suspend fun completeExchange(id: Long) : Pair<Boolean, String> = coroutineScope{
         return@coroutineScope try {
            val exchange = exchangeRepository.findById(id).orElseThrow { EntityNotFoundException()}
                exchange.apply {
                this.completed=true
            }
            exchangeRepository.save(exchange)
             return@coroutineScope Pair(true, "exchange was successfully completed")
        }catch (e : Exception){
            e.printStackTrace()
             return@coroutineScope Pair(false, "Exchange Exception: ${e.message}")
        }
    }


}