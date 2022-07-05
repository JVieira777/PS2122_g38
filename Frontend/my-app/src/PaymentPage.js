import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from './abi.json'
import contractAddresses from './contractAddress.json' 
import { Header} from './Header';
import {ethers} from 'ethers'

export function PaymentPage(exchange){
    const {isWeb3Enable, chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState("0")

    const chainId = parseInt(chainIdHex)
   
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : pay} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"pay",
        msgValue:msgValue,
        params: {_id : 5},
    })
    
    
    
useEffect(() => {
    if(isWeb3Enable){
        //setmsgValue(ethers.utils.formatUnits(15,"ether"))
        //setmsgValue(exchange.value)
        setmsgValue(15)
        console.log(msgValue)
    }
   
},[isWeb3Enable])
    
    
    return(
        <div>
            <Header />
            (<button onClick = {async () => {
                await pay()
                }}
                
                >
                Pay
                </button>)  
                
                
            
        
        </div>
    
    )


}