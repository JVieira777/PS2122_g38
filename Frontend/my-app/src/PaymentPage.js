import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from './abi.json'
import contractAddresses from './contractAddress.json' 
import { Header} from './Header';
import {ethers} from 'ethers'
import { GetExchange} from './Exchange'
import  { useNavigate } from 'react-router-dom'


export function PaymentPage(){
    const navigate = useNavigate()
    const { chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState(0)

    const chainId = parseInt(chainIdHex)
   
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : pay} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"pay",
        msgValue:msgValue,
        params: {_id : 6},
    })
    
    function cancel() {
        return navigate("/product")
    }
    
useEffect(() => {
        //setmsgValue(ethers.utils.formatUnits(15,"ether"))
        //setmsgValue(exchange.value)
        pay()
 },[msgValue])
    
    
    return(
        <div>
            <Header />
            
            (<button onClick = {() => {
                setmsgValue(15)
                }}
                >
                Pay
            </button>)  
            (<button onClick = {async () => {
                await cancel()
                }}
                >
                Cancel
            </button>)    
                
            
        
        </div>
    
    )
    /*(<button onClick = {async () => {
        await cancel()
        }}>
        cancel
        </button>)*/

}