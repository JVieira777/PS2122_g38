import  {useEffect,useState} from 'react'

import { GetExchangesFromUser} from './Exchange'
import  { useParams, useNavigate } from 'react-router-dom'
import '../pages/UserProfile.css'
import Cookies from 'universal-cookie'

import  {ExchangeViewModal} from '../Components/ExchangeViewModal'

export  function UserProfile(){
    const {id} = useParams()
    const navigate = useNavigate()
   
    const [Exchange,setexchange] = useState(0)
    const [Exchanges,setexchanges] = useState([])
    const [modal,setModal] = useState(false)
    const [beSeller,setbeSeller] = useState(true)
    const cookie_name = "User_Cookie"

    const cookie = new Cookies()

    const cookie_values = cookie.get(cookie_name)
   
    useEffect(() => {
        GetExchangesFromUser(id).then( response => {
            setexchanges(response.data)

        })
        
    },[setexchanges])


    useEffect(() => {
        if(cookie_values!==undefined){
            
            if(cookie_values.type==='Seller'){
               setbeSeller(false)
            }
           
            }
        
    },[cookie_values])

    if(Exchanges){
        return(
                    
            <div>

                <h1>
                    {Exchanges.map(exchange => 

                            
                        (
                            
                            <div key={exchange.id}>
                            <p>Exchange id: {exchange.id}</p>
                            <p>Product id: {exchange.pid}</p>
                            <p>value: {exchange.value}</p>
                            <p>quantity: {exchange.quantity}</p>
                            <p>totalprice: {exchange.quantity*exchange.value}</p>
                            <p>end date: {exchange.end_Date}</p>
                            <button onClick = {() => {
                                setexchange(exchange)
                                setModal(true)
                                 }}
                                     >
                                Info
                                </button>
                            </div> 
                        ))}
                </h1>
                <button className="custom-btn2 UserInfoButton" onClick={
                     () => {
                       navigate(`/user/info/${id}`)
                    }
                }
                    
                >
                    My Account
                </button>

                {beSeller && <button className="custom-btn2 BeaSeller" onClick={
                     () => {
                       navigate(`/user/profile/${id}/seller`)
                    }
                }
                    
                >
                    Became a Seller
                </button>}
                {modal && <ExchangeViewModal exchange = {Exchange}  setModal = {setModal}/>}
                    
            </div>
        
        )
    }
        
}
