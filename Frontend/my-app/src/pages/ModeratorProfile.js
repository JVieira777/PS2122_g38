import  { useNavigate,  useParams } from 'react-router-dom'

export function ModeratorProfile(){
    const {id} = useParams()
    const navigate = useNavigate()
    return(
        <div>
     
        (<button onClick = {() => {
            navigate("/moderator/refundRequest")
            }}
            >
            RefundRequests
        </button>)    
    </div>

    )

}