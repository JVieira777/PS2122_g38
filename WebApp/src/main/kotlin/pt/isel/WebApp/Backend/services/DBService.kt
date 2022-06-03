package pt.isel.WebApp.Backend.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.Backend.Entity.*
import pt.isel.WebApp.Backend.Repository.*
import java.util.UUID
import javax.persistence.EntityNotFoundException


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
    fun createImage(image : Image) : String{
        return try {
            imageRepository.save(image)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Image Exception: ${e.message}"
        }
    }

    fun getAllImages() : List<Image>? = imageRepository.findAll()

    fun getImage(id: UUID) : java.util.Optional<Image> = imageRepository.findById(id)


    fun GetallImageFromAProduct(id: UUID) : List<Image>? = imageRepository.findAllImagesFromProduct(id)

    fun DeleteImage(id: UUID) : String{
        return try {
            imageRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Image Exception: ${e.message}"
        }
    }


    //Moderator
    fun createModerator(mod : Moderator) : String{
        return try {
            moderatorRepository.save(mod)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Moderator Exception: ${e.message}"
        }
    }

    fun getAllModerators() : List<Moderator>? = moderatorRepository.findAll()

    fun getModerator(id: UUID) : java.util.Optional<Moderator> =  moderatorRepository.findById(id)

    fun DeleteModerator(id: UUID) : String{
        return try {
            val mod = moderatorRepository.getById(id)
            mod.apply {
                this.Terminated=true
            }
            moderatorRepository.save(mod)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Moderator Exception: ${e.message}"
        }
    }

    //Product
    fun createProduct(product: Product) : String{
        return try {
            productRepository.save(product)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }
    }

    fun getAllProducts() : List<Product>? =  productRepository.findAll()


    fun getProduct(id: UUID) : java.util.Optional<Product> = productRepository.findById(id)

    fun getAllProductsFromSeller(id : UUID) : List<Product>? = productRepository.findAllProductsFromSeller(id)

    fun DeleteProduct(id: UUID) : String{
        return try {
            productRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }
    }


    //Seller
    fun createSeller(seller : Seller) : String{
        return try {
            sellerRepository.save(seller)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Seller Exception: ${e.message}"
        }
    }

    fun getAllSellers() : List<Seller>? = sellerRepository.findAll()

    fun getSeller(id: UUID) : java.util.Optional<Seller> = sellerRepository.findById(id)

    fun DeleteSeller(id: UUID) : String{
        return try {
            val seller = sellerRepository.getById(id)
            seller.apply {
                this.Terminated=true
            }
            sellerRepository.save(seller)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Seller Exception: ${e.message}"
        }
    }



    //User
    fun createUser(user : User) : String{
        return try {
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUser(id: UUID) : java.util.Optional<User> = userRepository.findById(id)

    fun DeleteUser(id: UUID) : String{
        return try {
            val user = userRepository.getById(id)
            user.apply {
                this.Terminated=true
            }
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }
    fun UpdateUser(id: UUID, newuser: User) : String{
        return try {
            val user = userRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            user.apply {
                    this.rate = newuser.rate
                    this.EmailAddress = newuser.EmailAddress
                    this.Password = newuser.Password
                    this.ProfilePicture = newuser.ProfilePicture
                    this.Wallet = newuser.Wallet
            }
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }

    //Exchange
    fun createExchange(exchange: Exchange) : String{
        return try {
            exchangeRepository.save(exchange)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Exchange Exception: ${e.message}"
        }
    }

    fun getAllExchanges() : List<Exchange> = exchangeRepository.findAll()

    fun getExchange(id: UUID) : java.util.Optional<Exchange> = exchangeRepository.findById(id)

    fun getAllExchangesFromUser(id: UUID) : List<Exchange> = exchangeRepository.GetAllUserExchanges(id)

     fun DeleteExchange(id: UUID) : String{
        return try {
            val exchange = exchangeRepository.getById(id)
            exchange.apply {
                this.Terminated=true
            }
            exchangeRepository.save(exchange)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Exchange Exception: ${e.message}"
        }
    }




}