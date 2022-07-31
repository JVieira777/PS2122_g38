import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import {GetExchangesFromUser} from './Exchange'
import  { useNavigate,  useParams } from 'react-router-dom'
import { Header} from '../Components/Header';


//todo button
export function UserProfile(){
    const {id} = useParams()
    const navigate = useNavigate()
    
    const { chainId: chainIdHex} = useMoralis()
    const [ExchangeId,setexchangeId] = useState(0)
    const [Exchanges,setexchanges] = useState([])
    const chainId = parseInt(chainIdHex)


    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : refund} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"refund",
        params: {_id : ExchangeId},
    })
    

    useEffect(() => {
        GetExchangesFromUser(id).then( response => {
            setexchanges(response.data)
        })
        
    },[chainId,setexchanges])

    useEffect(() => {
        collect()
    },[ExchangeId])



    
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
                            console.log(exchange.id)
                            setexchangeId(exchange.id)
                            }}
                            >
                            Refund
                        </button>)
                        </div> 
                    ))}
                </h1>
            </div>
    
        )
    }
}
