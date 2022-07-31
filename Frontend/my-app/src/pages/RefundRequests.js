import { Header} from '../Components/Header';
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import  {useEffect,useState} from 'react'


export function RefundRequests(){
    const { chainId: chainIdHex} = useMoralis()
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null
    const chainId = parseInt(chainIdHex)
    
    const [ExchangeId,setexchangeId] = useState(0)
    
    const {runContractFunction : refund} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"refund",
        params: {_id : ExchangeId},
    })
}