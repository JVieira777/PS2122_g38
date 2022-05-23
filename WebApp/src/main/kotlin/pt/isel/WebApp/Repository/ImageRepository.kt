package pt.isel.WebApp.Repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository;
import pt.isel.WebApp.Entity.Image
import java.util.UUID

@Repository
interface ImageRepository : JpaRepository<Image,UUID>{
}