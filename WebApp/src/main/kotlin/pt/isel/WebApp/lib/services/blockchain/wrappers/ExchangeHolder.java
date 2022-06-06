package pt.isel.WebApp.lib.services.blockchain.wrappers;



import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;


import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class ExchangeHolder extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b0319163317905561068c806100326000396000f3fe6080604052600436106100555760003560e01c8063278ecde11461005a5780632839fc291461007c578063b6adaaff1461011f578063c290d6911461013f578063ce3f865f14610152578063f5c636c714610172575b600080fd5b34801561006657600080fd5b5061007a6100753660046105c6565b610192565b005b34801561008857600080fd5b506100de6100973660046105c6565b6001602081905260009182526040909120805491810154600282015460038301546004909301546001600160a01b0392831693919092169160ff8082169161010090041686565b604080519687526001600160a01b0395861660208801529390941692850192909252606084015215156080830152151560a082015260c00160405180910390f35b34801561012b57600080fd5b5061007a61013a3660046105c6565b61028f565b61007a61014d3660046105c6565b6102db565b34801561015e57600080fd5b5061007a61016d3660046105c6565b6103d7565b34801561017e57600080fd5b5061007a61018d3660046105df565b6104f1565b6000546001600160a01b03163381146101c65760405162461bcd60e51b81526004016101bd9061062a565b60405180910390fd5b60008281526001602052604090206004015460ff1680156101fe5750600082815260016020526040902060040154610100900460ff16155b6102405760405162461bcd60e51b8152602060048201526013602482015272273790333ab73239903a37903932b33ab7321760691b60448201526064016101bd565b600082815260016020819052604080832091820154915490516001600160a01b039092169281156108fc029290818181858888f1935050505015801561028a573d6000803e3d6000fd5b505050565b6000546001600160a01b03163381146102ba5760405162461bcd60e51b81526004016101bd9061062a565b506000908152600160205260409020600401805461ff001916610100179055565b6000818152600160205260409020543414801561030a575060008181526001602052604090206004015460ff16155b6103565760405162461bcd60e51b815260206004820152601d60248201527f496e636f7272656374207472616e73616374696f6e20616d6f756e742e00000060448201526064016101bd565b6000818152600160205260409020600201546001600160a01b03166103a35760405133903480156108fc02916000818181858888f193505050501580156103a1573d6000803e3d6000fd5b505b600090815260016020819052604090912080820180546001600160a01b03191633179055600401805460ff19169091179055565b6000818152600160205260409020600201546001600160a01b031633146104305760405162461bcd60e51b815260206004820152600d60248201526c24b73b30b634b2103ab9b2b91760991b60448201526064016101bd565b600081815260016020526040902060040154610100900460ff168015610467575060008181526001602052604090206004015460ff165b6104b35760405162461bcd60e51b815260206004820152601860248201527f4f72646572206e6f742079657420636f6d706c657465642e000000000000000060448201526064016101bd565b600081815260016020526040808220549051339282156108fc02929190818181858888f193505050501580156104ed573d6000803e3d6000fd5b5050565b6000546001600160a01b031633811461051c5760405162461bcd60e51b81526004016101bd9061062a565b506040805160c081018252938452600060208086018281526001600160a01b03958616878501908152606088019586526080880184815260a0890185815299855260019384905294909320965187555190860180549186166001600160a01b03199283161790559051600286018054919095169116179092555160038301555160049091018054925115156101000261ff00199215159290921661ffff1990931692909217179055565b6000602082840312156105d857600080fd5b5035919050565b600080600080608085870312156105f557600080fd5b843593506020850135925060408501356001600160a01b038116811461061a57600080fd5b9396929550929360600135925050565b6020808252601290820152712ab730baba3437b934bd32b2103ab9b2b91760711b60408201526060019056fea2646970667358221220fbe13d1a5c9d04080ab5c3c3e3fe2ae038ca918b1b60a55265e9baf2b0a01fc164736f6c63430008070033";

    public static final String FUNC_COLLECT = "collect";

    public static final String FUNC_COMPLETEORDER = "completeOrder";

    public static final String FUNC_EXCHANGES = "exchanges";

    public static final String FUNC_NEWEXCHANGE = "newExchange";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_REFUND = "refund";

    @Deprecated
    protected ExchangeHolder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ExchangeHolder(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ExchangeHolder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ExchangeHolder(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> collect(BigInteger _id) {
        final Function function = new Function(
                FUNC_COLLECT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> completeOrder(BigInteger _id) {
        final Function function = new Function(
                FUNC_COMPLETEORDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>> exchanges(BigInteger param0) {
        final Function function = new Function(FUNC_EXCHANGES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>>(function,
                new Callable<Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, String, BigInteger, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> newExchange(BigInteger _id, BigInteger _price, String _destination, BigInteger _end_date) {
        final Function function = new Function(
                FUNC_NEWEXCHANGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(160, _destination), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(BigInteger _id) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> refund(BigInteger _id) {
        final Function function = new Function(
                FUNC_REFUND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ExchangeHolder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExchangeHolder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ExchangeHolder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExchangeHolder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ExchangeHolder load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ExchangeHolder(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ExchangeHolder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ExchangeHolder(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ExchangeHolder> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ExchangeHolder.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ExchangeHolder> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ExchangeHolder.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ExchangeHolder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExchangeHolder.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ExchangeHolder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExchangeHolder.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
