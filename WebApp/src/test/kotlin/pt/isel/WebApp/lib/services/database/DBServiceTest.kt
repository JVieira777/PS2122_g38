package pt.isel.WebApp.lib.services.database


import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import pt.isel.WebApp.lib.services.database.Entity.Exchange
import pt.isel.WebApp.lib.services.database.Entity.Seller
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class DBServiceTest {


    @Autowired
    private lateinit var dbService: DBService


    @Test
    fun connectionTest(){

    }


    @Test
    fun addImage() {

    }

    @Test
    fun getImages() {
    }

    @Test
    fun getImage() {
    }

    @Test
    fun getProductImages() {
    }

    @Test
    fun deleteImage() {
    }

    @Test
    fun createModerator() {
    }

    @Test
    fun getModerators() {
    }

    @Test
    fun getModerator() {
    }

    @Test
    fun deleteModerator() {
    }

    @Test
    fun createProduct() {
    }

    @Test
    fun getProducts() {
    }

    @Test
    fun getProduct() {
    }

    @Test
    fun getSellerProducts() {
    }

    @Test
    fun deleteProduct() {
    }

    @Test
    fun createSeller() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)
        val seller = Seller(
            UUID(0L, 0L),
            "asdasd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user.id
        )
        dbService.createSeller(seller)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(seller.equals(actualSeller.get()))

    }

    @Test
    fun getSellers() {
        val user = User(
            UUID(0L, 23L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val user1 = User(
            UUID(0L, 2L),
            "asdasxzczxd",
            "test3.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)
        dbService.createUser(user1)
        val seller = Seller(
            UUID(0L, 0L),
            "asdaaszsd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user.id
        )
        val seller1 = Seller(
            UUID(0L, 2L),
            "asdasdasd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user1.id
        )
        dbService.createSeller(seller)
        dbService.createSeller(seller1)
        val testSellers : List<Seller> = listOf(seller,seller1)
        println("testSellers: " + testSellers.toString())
        val allSellers: List<Seller>? = dbService.getSellers()
        println("allSellers: " + allSellers.toString())
        assert(testSellers.equals(allSellers))

    }

    @Test
    fun getSeller() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)
        val seller = Seller(
            UUID(0L, 0L),
            "asdasd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user.id
        )
        dbService.createSeller(seller)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(seller.equals(actualSeller.get()))
    }

    @Test
    fun updateSeller() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val seller = Seller(
            UUID(0L, 0L),
            "asdasd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user.id
        )
        val seller1 = Seller(
            UUID(0L, 0L),
            "asdasd",
            "EU",
            "im a seller",
            8.0f,
            false,
            user.id
        )
        dbService.createSeller(seller)
        dbService.updateSeller(seller.id,seller1)
        val actualSeller: Optional<Seller> = dbService.getSeller(user.id)
        print("actual Seller: " + actualSeller.toString())
        assert(actualSeller.get().rate ==8.0f)
    }

    @Test
    fun deleteSeller() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)
        val seller = Seller(
            UUID(0L, 0L),
            "asdasd",
            "EU",
            "im a seller",
            0.0f,
            false,
            user.id
        )
        dbService.createSeller(seller)
        dbService.deleteSeller(seller.id)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(actualSeller.get().terminated)
    }

    @Test
    fun createUser() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)

        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))

    }

    @Test
    fun getUsers() {
        val user = User(
            UUID(0L, 23L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val user1 = User(
            UUID(0L, 2L),
            "asdasxzczxd",
            "test3.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val user2 = User(
            UUID(0L, 4L),
            "asdasdasasd",
            "test2.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val testUsers : List<User> = listOf(user,user1,user2)
        println("testusers: " + testUsers.toString())
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createUser(user2)

        val allUsers: List<User> = dbService.getUsers()
        println("allusers: " + allUsers.toString())
        assert(testUsers.equals(allUsers))
    }

    @Test
    fun getUser() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)

        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))
    }

    @Test
    fun deleteUser() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)
        dbService.deleteUser(user.id)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(actualUser.get().terminated)
    }

    @Test
    fun updateUser() {
        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val user2 = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            8.0f,
            "",
            ""
        )
        dbService.createUser(user)
        dbService.updateUser(user.id,user2)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(actualUser.get().rate ==8.0f)
    }

    @Test
    fun createExchange() {

        val user = User(
            UUID(0L, 0L),
            "asdasd",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        val user2 = User(
            UUID(0L, 2L),
            "asdaqwesd",
            "test2.user@email.com",
            "testPASSWORD",
            8.0f,
            "",
            ""
        )
        dbService.createUser(user)
        dbService.createUser(user2)



        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))
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
}