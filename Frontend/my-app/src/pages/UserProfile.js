import  {useEffect,useState} from 'react'

import {GetExchangesFromUser} from './Exchange'
import  { useNavigate,  useParams } from 'react-router-dom'
import { Header} from '../Components/Header';
import { RefundRequestModal } from '../Components/RefundRequestModal';


//todo button
export function UserProfile(){
    const {id} = useParams()
    const navigate = useNavigate()
    
    const [Exchange,setexchange] = useState()
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
                    <Header />
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
                            Refund
                        </button>)
                        </div> 
                    ))}
                </h1>
                {modal && <RefundRequestModal exchange = {Exchange}  setModal = {setModal} />}
            </div>
    
        )
    }
}
