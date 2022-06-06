package pt.isel.WebApp.lib.services.database

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import pt.isel.WebApp.lib.services.Services
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*

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
    }

    @Test
    fun getSellers() {
    }

    @Test
    fun getSeller() {
    }

    @Test
    fun deleteSeller() {
    }

    @Test
    fun createUser() {

        val user = User(
            UUID(0L, 0L),
            "jj",
            "test.user@email.com",
            "testPASSWORD",
            0.0f,
            "",
            ""
        )
        dbService.createUser(user)

        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.get().toString())
        assert(user.equals(actualUser.get()))

    }

    @Test
    fun getUsers() {
    }

    @Test
    fun getUser() {
    }

    @Test
    fun deleteUser() {
    }

    @Test
    fun updateUser() {
    }

    @Test
    fun createExchange() {
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