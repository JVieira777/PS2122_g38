package pt.isel.WebApp.services.database.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.services.database.Entity.Image
import java.util.*

@Repository
interface ImageRepository : JpaRepository<Image,UUID>{
    @Query("SELECT i FROM Image i WHERE i.pid = ?1")
    fun findAllImagesFromProduct(id: UUID): kotlin.collections.List<Image>
}