package pt.isel.WebApp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.Entity.*
import pt.isel.WebApp.Repository.*
import java.util.UUID


@Service
class DBService () {
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
    fun createImage(image : Image) : Boolean{
        return try {
            imageRepository.save(image)
            true
        }catch (e : Exception){
            println("Image Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllImages() : List<Image>? = imageRepository.findAll()

    fun getImage(id: UUID) : java.util.Optional<Image> = imageRepository.findById(id)


    fun GetallImageFromAProduct(id: UUID) : List<Image>? = imageRepository.findAllImagesFromProduct(id)

    fun DeleteImage(id: UUID) : Boolean{
        return try {
            imageRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("Image Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }


    //Moderator
    fun createModerator(mod : Moderator) : Boolean{
        return try {
            moderatorRepository.save(mod)
            true
        }catch (e : Exception){
            println("Moderator Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllModerators() : List<Moderator>? = moderatorRepository.findAll()

    fun getModerator(id: UUID) : java.util.Optional<Moderator> =  moderatorRepository.findById(id)

    fun DeleteModerator(id: UUID) : Boolean{
        return try {
            moderatorRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("Moderator Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    //Product
    fun createProduct(product: Product) : Boolean{
        return try {
            productRepository.save(product)
            true
        }catch (e : Exception){
            println("Product Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllProducts() : List<Product>? =  productRepository.findAll()


    fun getProduct(id: UUID) : java.util.Optional<Product> = productRepository.findById(id)

    fun DeleteProduct(id: UUID) : Boolean{
        return try {
            productRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("Product Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }


    //Seller
    fun createSeller(seller : Seller) : Boolean{
        return try {
            sellerRepository.save(seller)
            true
        }catch (e : Exception){
            println("Seller Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllSellers() : List<Seller>? = sellerRepository.findAll()

    fun getSeller(id: UUID) : java.util.Optional<Seller> = sellerRepository.findById(id)

    fun DeleteSeller(id: UUID) : Boolean{
        return try {
            sellerRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("Seller Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }



    //User
    fun createUser(user : User) : Boolean{
        return try {
            userRepository.save(user)
            true
        }catch (e : Exception){
            println("User Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUser(id: UUID) : java.util.Optional<User> = userRepository.findById(id)

    fun DeleteUser(id: UUID) : Boolean{
        return try {
            userRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("User Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    //Exchange
    fun createExchange(exchange: Exchange) : Boolean{
        return try {
            exchangeRepository.save(exchange)
            true
        }catch (e : Exception){
            println("Exchange Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getAllExchanges() : List<Exchange> = exchangeRepository.findAll()

    fun getExchange(id: UUID) : java.util.Optional<Exchange> = exchangeRepository.findById(id)

    fun DeleteExchange(id: UUID) : Boolean{
        return try {
            exchangeRepository.deleteById(id)
            true
        }catch (e : Exception){
            println("Exchange Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }




}