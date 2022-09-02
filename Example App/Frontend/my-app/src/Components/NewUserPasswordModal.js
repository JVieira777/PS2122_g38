import React, { useState } from 'react'
import '../Components/NewUserEmailModal.css'
import axios from 'axios'



export function NewUserPasswordModal({user,setModal}){

    const url = `http://localhost:8082/api/user/${user.id}`
    const [newPassword, setNewPassword] = useState(" ")
    const [oldPassword, setOldPassword] = useState(" ")


    function UpdatePassword() {

        if(oldPassword === user.password){
        axios.put(url, {
            id: user.id,
            username: user.username,
            emailAddress: user.emailAddress,
            password: newPassword,
            rate: 0.0,
            profilePicture: ""
        }).then(() => {
            setModal(false)
        })
        }else{
            setModal(false)
            
        }
    }

    
    function handleValuesOld(e) {
        setOldPassword(e.target.value)
    }

    function handleValuesNew(e) {
        setNewPassword(e.target.value)
    }
   
    return (
        <div className='ModalBackground4'>
        <div className='Content4'>
        <p>
        <label>Old Password</label>
        <input type="text" id='oldPassword'  onChange={(e) => handleValuesOld(e)}></input>
        </p>
        <p>
        <label>New Password</label>
        <input type="text" id='newPassword'  onChange={(e) => handleValuesNew(e)}></input>
        </p>

        <button onClick = {() => {
        setModal(false)
        }}
        >
        Cancel
        </button>  
        
        <button onClick = { () =>  {
        UpdatePassword()
        }}
        >
        Confirm
        </button> 
   
    </div>
    
    </div>
    )
    
}