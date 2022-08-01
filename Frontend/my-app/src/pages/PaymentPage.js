import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import { Header} from '../Components/Header';
import {GetBlockchainExchangeInfo} from './Exchange'
import  { useNavigate,  useParams } from 'react-router-dom'


export function PaymentPage(){
    const {id} = useParams()
    const navigate = useNavigate()
    const { chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState(0)
    const [exchange,setexchange] = useState(0)
    const chainId = parseInt(chainIdHex)
   
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
        pay()
 },[msgValue])
    
    
    return(
        <div>
            <Header />
            <h1>
                    {
                        <div >
                        <p>Price: {exchange.value1}</p>
                        <p>destination: {exchange.value3}</p>
                        <p>end date: {exchange.value4}</p>
                        </div>
                    }
            </h1>
            (<button onClick = {() => {
                setmsgValue(exchange.value1)
                }}
                >
                Pay
            </button>)  
            (<button onClick = {() => {
                 cancel()
                }}
                >
                Cancel
            </button>)    
                
            
        
        </div>
    
    )
}