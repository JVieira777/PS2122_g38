package pt.isel.WebApp.Backend.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.isel.WebApp.Backend.Entity.Image
import java.util.*

@Repository
interface ImageRepository : JpaRepository<Image,UUID>{
    @Query("SELECT i FROM Image i WHERE i.pid = ?1")
    fun findAllImagesFromProduct(id: UUID): List<Image>
}