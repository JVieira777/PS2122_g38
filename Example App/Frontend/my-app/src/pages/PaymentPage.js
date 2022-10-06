import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import {GetBlockchainExchangeInfo} from './Exchange'
import  { useNavigate,  useParams } from 'react-router-dom'

//TODO sair da pagina
export function PaymentPage(){
    const {id} = useParams()
    const navigate = useNavigate()
    const { chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState(0)
    const [navigatepage,setNavigatepage] = useState(false)
    const [exchange,setexchange] = useState(0)
    const chainId = parseInt(chainIdHex)
    const [end_Date,setendDate] = useState()

    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : pay} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"pay",
        msgValue:msgValue,
        params: {_id : id},
    })
    
    function cancel() {
        return navigate("/product")
    }
    
    useEffect(() => {
        GetBlockchainExchangeInfo(id).then(response =>
            setexchange(response.data)
            )
    },[chainId,setexchange])

    useEffect(() => {
        console.log(exchange.end_date)
        if(exchange.end_date !== undefined){
       setendDate(new Date(exchange.end_date).toDateString())
        }
    },[exchange,setexchange])

useEffect(() => {
    console.log(msgValue)
        pay()
     
 },[msgValue])
    
 useEffect(() => {
    if(navigatepage === true){
        return navigate("/product")
    }
 },[navigatepage,setNavigatepage])
    
    return(
        <div>
            <h1>
                    {
                        <div>
                        <p>ID: {id} </p>
                        <p>Price: {exchange.price} Wei</p>
                        <p>destination: {exchange.sellerAddress}</p>
                        <p>end date: {end_Date}</p>
                        </div>
                    }
            </h1>
            <button onClick = {() => {
                setmsgValue(exchange.price)
                setNavigatepage(true)
                }}
                >
                Pay
            </button>  
            <button onClick = {() => {
                 cancel()
                }}
                >
                Cancel
            </button>    
                
            
        
        </div>
    
    )
}