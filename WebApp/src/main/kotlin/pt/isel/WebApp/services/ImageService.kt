package pt.isel.WebApp.services

import org.springframework.stereotype.Service
import pt.isel.WebApp.Entity.Image
import pt.isel.WebApp.Repository.ImageRepository
import java.util.UUID

@Service
class ImageService (private val imageRepository: ImageRepository) {

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

    fun getAllImages(image : Image) : Boolean{
        return try {
            imageRepository.findAll()
            true
        }catch (e : Exception){
            println("Image Exception: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    fun getImage(id: UUID) : Boolean{
        return try {
            imageRepository.findById(id)
            true
        }catch (e : Exception){
            println("Image Exception: ${e.message}")
            e.printStackTrace()
            false
        }

    }

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


}