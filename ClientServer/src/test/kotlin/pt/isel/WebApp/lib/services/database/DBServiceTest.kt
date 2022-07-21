package pt.isel.WebApp.lib.services.database



import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pt.isel.WebApp.WebAppApplication
import pt.isel.WebApp.lib.services.database.Entity.*
import java.util.*
import kotlin.streams.asSequence


@SpringBootTest(
    classes = arrayOf(WebAppApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class DBServiceTest {


    @Autowired
    private lateinit var dbService: DBService




    @Test
    fun addImage() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        dbService.addImage(image)
        val actualImage: Image = dbService.getImage(image.id).second
        print("actual Image: $actualImage")
        assert(image.equals(actualImage))
    }

    @Test
    fun getImages() = runBlocking{
        imageSetup()
        val testImages : List<Image> = listOf(image,image1,image2)
        println("testImages: $testImages")
        val allImages: List<Image> = dbService.getImages().second
        println("allImages: $allImages")
        assert(testImages.equals(allImages))

    }

    @Test
    fun getImage() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        dbService.addImage(image)
        val actualImage: Image = dbService.getImage(image.id).second
        print("actual Image: $actualImage")
        assert(image.equals(actualImage))
    }

    @Test
    fun getProductImages() = runBlocking{
        imageSetup()
        val testImages : List<Image> = listOf(image,image1)
        println("testImages: $testImages")
        val allImages: List<Image> = dbService.getProductImages(product.id).second
        println("allImages: $allImages")
        assert(testImages.equals(allImages))
    }

    fun imageSetup()= runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        dbService.addImage(image)
        dbService.addImage(image1)
        dbService.addImage(image2)
    }
    @Test
    fun deleteImage() = runBlocking{
        imageSetup()
        val testImages : List<Image> = listOf(image,image1)
        println("testImages: $testImages")
        dbService.deleteImage(image2.id)
        val allImages: List<Image> = dbService.getImages().second
        println("allImages: $allImages")
        assert(testImages.equals(allImages))

    }

    @Test
    fun createModerator() = runBlocking{
        dbService.createUser(user)
        dbService.createModerator(moderator)
        val actualmoderator: Moderator = dbService.getModerator(moderator.id).second
        print("actual seller: $actualmoderator")
        assert(moderator.equals(actualmoderator))
    }

    @Test
    fun getModerators() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createModerator(moderator)
        dbService.createModerator(moderator1)
        val testModerators : List<Moderator> = listOf(moderator,moderator1)
        println("testModerators: $testModerators")
        val allModerators: List<Moderator> = dbService.getModerators().second
        println("allModerators: $allModerators")
        assert(testModerators.equals(allModerators))
    }

    @Test
    fun getModerator() = runBlocking{
        dbService.createUser(user)
        dbService.createModerator(moderator)
        val actualmoderator: Moderator = dbService.getModerator(moderator.id).second
        print("actual seller: $actualmoderator")
        assert(moderator.equals(actualmoderator))
    }

    @Test
    fun deleteModerator() = runBlocking{
        dbService.createUser(user)
        dbService.createModerator(moderator)
        dbService.deleteModerator(moderator.id)
        val actualModerator: Moderator = dbService.getModerator(moderator.id).second
        print("actual Moderator: $actualModerator")
        assert(actualModerator.terminated)
    }

    @Test
    fun createProduct() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        val actualProduct: Product = dbService.getProduct(product.id).second
        print("actual product: $actualProduct")
        assert(product.equals(actualProduct))
    }

    @Test
    fun getProducts() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1,product2)
        println("testProducts: $testProducts")
        val actualProducts: List<Product> = dbService.getProducts().second
        print("actual products: $actualProducts")
        assert(testProducts.equals(actualProducts))
    }

    @Test
    fun getProduct() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        val actualProduct: Product = dbService.getProduct(product.id).second
        print("actual product: $actualProduct")
        assert(product.equals(actualProduct))
    }

    @Test
    fun getSellerProducts() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1)
        println("testProducts: $testProducts")
        val actualProducts: List<Product> = dbService.getSellerProducts(seller.id).second
        print("actual products: $actualProducts")
        assert(testProducts.equals(actualProducts))
    }


    @Test
    fun getProductsByName() = runBlocking{
        val actualProducts: List<Product> = dbService.getProductsByName("table").second
        print("actual product: $actualProducts")
        assert("table" == actualProducts.first().name)
    }

    @Test
    fun deleteProduct() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1)
        println("testProducts: $testProducts")
        dbService.deleteProduct(product2.id)
        val actualProducts: List<Product> = dbService.getProducts().second
        print("actual products: $actualProducts")
        assert(testProducts.equals(actualProducts))
    }

    @Test
    fun createSeller() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        val actualSeller:Seller = dbService.getSeller(seller.id).second
        print("actual seller: $actualSeller")
        assert(seller.equals(actualSeller))

    }

    @Test
    fun getSellers() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        val testSellers : List<Seller> = listOf(seller,seller2)
        println("testSellers: $testSellers")
        val allSellers: List<Seller> = dbService.getSellers().second
        println("allSellers: $allSellers")
        assert(testSellers.equals(allSellers))

    }

    @Test
    fun getSeller()= runBlocking {
        dbService.createUser(user)
        dbService.createSeller(seller)
        val actualSeller: Seller = dbService.getSeller(seller.id).second
        print("actual seller: $actualSeller")
        assert(seller.equals(actualSeller))
    }

    @Test
    fun updateSeller() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        val sellerup = Seller(
            seller.id,
            seller.name,
            "EU",
            "im a seller",
            8.0f,
            seller.wallet,
            false,
            user1.id
        )
        dbService.updateSeller(seller.id,sellerup)
        val actualSeller: Seller = dbService.getSeller(seller.id).second
        print("actual Seller: $actualSeller")
        assert(actualSeller.rate ==8.0f)
    }

    @Test
    fun deleteSeller() = runBlocking{
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.deleteSeller(seller.id)
        val actualSeller: Seller = dbService.getSeller(seller.id).second
        print("actual seller: $actualSeller")
        assert(actualSeller.terminated)
    }

    @Test
   fun createUser() = runBlocking{
        val x = async { dbService.createUser(user) }.await().second

        val actualUser: User = dbService.getUser(user.id).second
        print("actual user: $actualUser")
        assert(user == actualUser)
   }

    @Test
    fun getUsers() = runBlocking{
        val testUsers : List<User> = listOf(user,user1,user2)
        println("test_users: $testUsers")
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createUser(user2)

        val allUsers: List<User> = dbService.getUsers().second
        println("all_users: $allUsers")
        assert(testUsers == allUsers)
    }

    @Test
    fun getUser()= runBlocking {
        dbService.createUser(user)
        val actualUser: User = dbService.getUser(user.id).second
        print("actual user: $actualUser")
        assert(user == actualUser)
    }

    @Test
    fun deleteUser() = runBlocking{
        dbService.createUser(user)
        dbService.deleteUser(user.id)
        val actualUser: User = dbService.getUser(user.id).second
        print("actual user: $actualUser")
        assert(actualUser.terminated)
    }

    @Test
    fun updateUser() = runBlocking{
        dbService.createUser(user)
        dbService.getUser(user.id)
        val userup = User(
            user.id,
            user.username,
            user.emailAddress,
            user.password,
            8.0f,
            ""
        )
        dbService.updateUser(user.id,userup)
        val actualUser: User = dbService.getUser(user.id).second
        print("actual user: $actualUser")
        assert(actualUser.rate ==8.0f)
    }


    @Test
    fun createExchange() = runBlocking{
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product2)
        val ex = dbService.createExchange(exchange)
        println(ex.second)
        assert(ex.first)
    }

    @Test
    fun getExchanges() {
    }

    @Test
    fun getExchange() {
    }

    @Test
    fun getUserExchanges() {
    }

    @Test
    fun deleteExchange() {
    }
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val user = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )
    private val user1 = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )
    private val user2 = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )


    val seller = Seller(
        UUID.randomUUID(),
        "asdasd",
        "EU",
        "im a seller",
        0.0f,
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        false,
        user.id
    )

    val seller2 = Seller(
        UUID.randomUUID(),
        "asdaasdasdsd",
        "EU",
        "im a seller",
        4.0f,
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        false,
        user1.id
    )



    val moderator = Moderator(
        UUID.randomUUID(),
        "asdasd",
        "im a mod",
        false,
        user.id
    )
    val moderator1 = Moderator(
        UUID.randomUUID(),
        "asdsadsaasd",
        "im a mod1",
        false,
        user1.id
    )

    val product = Product(
        UUID.randomUUID(),
        "asdasd",
        "cards",
        35,
        2.0f,
        seller.id
    )
    val product1 = Product(
        UUID.randomUUID(),
        "asdassadd",
        "plushness",
        25,
        2.0f,
        seller.id
    )
    val product2 = Product(
        UUID.randomUUID(),
        "asdaswwwsadd",
        "plushnasdess",
        245,
        2.0f,
        seller2.id
    )
    val image = Image(
        UUID.randomUUID(),
        "asdasd",
        product.id
    )
    val image1 = Image(
        UUID.randomUUID(),
        "asdazxcxzcsd",
        product.id
    )
    val image2 = Image(
        UUID.randomUUID(),
        "asd",
        product1.id
    )

    val exchange = Exchange(
        0,
        user.id,
        seller2.id,
        product2.id,
        12,
        1,
        Date()
    )

    private val usera = User(
        UUID.randomUUID(),
        "marcoff",
        "marcoff@gmail.com",
        "testPASSWORD",
        0.0f,
        ""
    )
    private val userb = User(
        UUID.randomUUID(),
        "gina2",
        "gg@gmail.com",
        "293021",
        0.0f,
        ""
    )
    private val userc = User(
        UUID.randomUUID(),
        "suzette",
        "suzette_mm@gmail.com",
        "asdee",
        0.0f,
        ""
    )
    private val userd = User(
        UUID.randomUUID(),
        "manuelss",
        "ff15@gmail.com",
        "xxw2",
        0.0f,
        ""
    )
    private val usere = User(
        UUID.randomUUID(),
        "rodrigao",
        "rdrbb@gmail.com",
        "1233",
        0.0f,
        ""
    )

    @Test
    fun initDB() = runBlocking{
        dbService.createUser(usera)
        dbService.createUser(userb)
        dbService.createUser(userc)
        dbService.createUser(userd)
        dbService.createUser(usere)
        dbService.createSeller(sellera)
        dbService.createSeller(sellerb)
        dbService.createProduct(producta)
        dbService.createProduct(productb)
        dbService.createProduct(productc)
        dbService.createModerator(moderatora)
        delay(5000)

    }


    val sellera = Seller(
        UUID.randomUUID(),
        "asdasd",
        "Malta",
        "im a seller",
        0.0f,
        "0x000000001",
        false,
        usera.id
    )

    val sellerb = Seller(
        UUID.randomUUID(),
        "antonio",
        "Espanha",
        "im a seller",
        0.0f,
        "0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590",
        false,
        userc.id
    )

    val producta = Product(
        UUID.randomUUID(),
        "asdasd",
        "cards",
        35,
        2.0f,
        sellera.id
    )
    val productb = Product(
        UUID.randomUUID(),
        "asdassadd",
        "plushness",
        25,
        2.0f,
        sellera.id
    )

    val productc = Product(
        UUID.randomUUID(),
        "table",
        "wood",
        45,
        2.0f,
        sellerb.id
    )
    val moderatora = Moderator(
        UUID.randomUUID(),
        "asdsadsaasd",
        "im a mod1",
        false,
        userb.id
    )

}

