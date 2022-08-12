import  {useEffect,useState} from 'react'

import { GetExchangesFromUser} from './Exchange'
import  { useParams } from 'react-router-dom'
import { Header} from '../Components/Header';


import  {ExchangeViewModal} from '../Components/ExchangeViewModal'

export  function UserProfile(){
    const {id} = useParams()

   
    const [Exchange,setexchange] = useState(0)
    const [Exchanges,setexchanges] = useState([])
    const [modal,setModal] = useState(false)
 
   
   
    useEffect(() => {
        GetExchangesFromUser(id).then( response => {
            setexchanges(response.data)

        })
        
    },[setexchanges])

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
                            (<button onClick = {() => {
                                setexchange(exchange)
                                setModal(true)
                                 }}
                                     >
                                Info
                                </button>)
                            </div> 
                        ))}
                </h1>
                {modal && <ExchangeViewModal exchange = {Exchange}  setModal = {setModal}/>}
                    
            </div>
        
        )
    }
        
}
