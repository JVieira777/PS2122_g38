import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'
import '../pages/UserInfo.css'
import { NewUserEmailModal } from '../Components/NewUserEmailModal'
import { NewUserPasswordModal } from '../Components/NewUserPasswordModal'

export  function UserInfo(){
    const { id } = useParams()
    const url = `http://localhost:8082/api/user/${id}`
    const [user, setUser] = useState()
    const [modalPassword,setModalPassword] = useState(false)
    const [modalEmailAddress,setModalEmailAddress] = useState(false)

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setUser(response.data)
            })
    }, [url])
    if (user) {
        return (
            <div className='wrapUser'>
                <div className='user'>
                <h1>

                    <div  key={user.id}>
                        <h2><p>Username: {user.username}</p></h2>
                        <h2><p>
                            EmailAddress: {user.emailAddress}
                            <button onClick = {() => {
                                setModalEmailAddress(true)
                                 }}
                                     >
                                Change EmailAddress
                            </button>
                        
                        </p></h2>
                        <h2><p>password: {user.password}
                        <button onClick = {() => {
                                setModalPassword(true)
                                 }}
                                     >
                                Change Password
                            </button>
                        </p></h2>
                        <h2><p>Rating: {user.rate}</p></h2>

                    </div>
                    
                </h1>
                {modalEmailAddress && <NewUserEmailModal user = {user}  setModal = {setModalEmailAddress}/>}
                {modalPassword && <NewUserPasswordModal user = {user}  setModal = {setModalPassword}/>}
                </div>
            </div>

        )
    }
        
}