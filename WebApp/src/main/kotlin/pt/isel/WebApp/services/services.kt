package pt.isel.WebApp.services

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.services.blockchain.ExchangeService
import pt.isel.WebApp.services.database.DBService
import pt.isel.WebApp.services.database.Entity.User
import java.util.*


/**
 *
 * return raw data from api
 *
 *
 */
@Component
class services() {

    @Autowired
    lateinit var dbService: DBService;

    @Autowired
    lateinit var blockchainService: ExchangeService ;

    fun addUser(user: User){
        dbService.createUser(user)
    }

    fun getUser(user: User){
        dbService.createUser(user)
    }



    //web3j-cli-1.4.1-all.jar generate solidity -a=

    //generate solidity -a <abiFile_path> -b <binFile_path> -o <output_destination> -p <package>

}

@RestController
@RequestMapping("/api")
class ApiController(){
    @Autowired
    lateinit var service: services;

    @GetMapping("/{uid}")
    fun GetUser(@PathVariable("uid") user_id: String) : Optional<User> = service.getUser(UUID.fromString(user_id))



}