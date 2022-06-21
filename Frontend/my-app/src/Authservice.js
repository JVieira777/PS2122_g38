import React, {useEffect,useState} from 'react'
import axios from 'axios'
import Cookies from "universal-cookie";

function Signup(){
    const url = 'http://localhost:8081/api/user'
    const [user,setUser] = useState({
        username: "",
        email: "",
        password: ""
    })

    function handleSignup(e){
        e.preventDefault()
        axios.post(url,{
            username: user.username,
            email: user.email,
            password: user.password
        }).then(res => {
            console.log("User successfully created with the name:"+res.username)
        })
    }

    function handleValues(e){
        const newuser ={...user}
        newuser[e.target.id] = e.target.value
        setUser(newuser)
    } 


    return(
        <div>
            <form onSubmit={(e) => handleSignup(e)}>
                    <label>Username</label>
                    <input type="text" id='name' value={user.username}  onChange={(e) => handleValues(e)}></input>
                    <label>Email Adress</label>
                    <input type="text" id='email' value={user.email} onChange={(e) => handleValues(e)}></input>
                    <label>Password</label>
                    <input type="text" id='password' value={user.password} onChange={(e) => handleValues(e)}></input>
                    <button>Signup</button>
            </form>
        </div>
    )

}

export default Signup