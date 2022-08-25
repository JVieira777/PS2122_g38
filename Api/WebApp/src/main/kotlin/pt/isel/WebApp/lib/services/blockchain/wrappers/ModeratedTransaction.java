package pt.isel.WebApp.lib.services.blockchain.wrappers;



import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class ModeratedTransaction extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161087e38038061087e833981810160405281019061003291906100eb565b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600381905550505061019a565b6000815190506100d08161016c565b92915050565b6000815190506100e581610183565b92915050565b6000806040838503121561010257610101610167565b5b6000610110858286016100c1565b9250506020610121858286016100d6565b9150509250929050565b60006101368261013d565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600080fd5b6101758161012b565b811461018057600080fd5b50565b61018c8161015d565b811461019757600080fd5b50565b6106d5806101a96000396000f3fe60806040526004361061007b5760003560e01c8063938b5f321161004e578063938b5f32146100ea578063a035b1fe14610115578063b269681d14610140578063e8fda6771461016b5761007b565b80631b9265b814610080578063590e1ae31461008a578063698996f8146100945780638da5cb5b146100bf575b600080fd5b610088610175565b005b610092610251565b005b3480156100a057600080fd5b506100a9610364565b6040516100b69190610599565b60405180910390f35b3480156100cb57600080fd5b506100d461036a565b6040516100e19190610543565b60405180910390f35b3480156100f657600080fd5b506100ff61038e565b60405161010c919061055e565b60405180910390f35b34801561012157600080fd5b5061012a6103b4565b6040516101379190610599565b60405180910390f35b34801561014c57600080fd5b506101556103ba565b604051610162919061055e565b60405180910390f35b6101736103e0565b005b600560009054906101000a900460ff1661024f57600354341061024e576001600560006101000a81548160ff02191690831515021790555033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060035434111561024d573373ffffffffffffffffffffffffffffffffffffffff166108fc6003543461022091906105c5565b9081150290604051600060405180830381858888f1935050505015801561024b573d6000803e3d6000fd5b505b5b5b565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146102e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102d790610579565b60405180910390fd5b600560009054906101000a900460ff161561036157600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6003549081150290604051600060405180830381858888f1935050505015801561035f573d6000803e3d6000fd5b505b50565b60045481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461046f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161046690610579565b60405180910390fd5b600560009054906101000a900460ff16156104f057600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6003549081150290604051600060405180830381858888f193505050501580156104ee573d6000803e3d6000fd5b505b50565b6104fc8161060b565b82525050565b61050b816105f9565b82525050565b600061051e6016836105b4565b915061052982610676565b602082019050919050565b61053d8161063d565b82525050565b60006020820190506105586000830184610502565b92915050565b600060208201905061057360008301846104f3565b92915050565b6000602082019050818103600083015261059281610511565b9050919050565b60006020820190506105ae6000830184610534565b92915050565b600082825260208201905092915050565b60006105d08261063d565b91506105db8361063d565b9250828210156105ee576105ed610647565b5b828203905092915050565b60006106048261061d565b9050919050565b60006106168261061d565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f53656e646572206e6f7420617574686f72697a65642e0000000000000000000060008201525056fea26469706673582212205bb91fec7e814b8ccc39a9a92352b69b6b18d1b3707880e1e3f19d29db5bff6964736f6c63430008070033";

    public static final String FUNC_COMPLETEPAYMENT = "completePayment";

    public static final String FUNC_CURRENTVALUE = "currentValue";

    public static final String FUNC_DESTINATION = "destination";

    public static final String FUNC_ORIGIN = "origin";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_REFUND = "refund";

    @Deprecated
    protected ModeratedTransaction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ModeratedTransaction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ModeratedTransaction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ModeratedTransaction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> completePayment() {
        final Function function = new Function(
                FUNC_COMPLETEPAYMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> currentValue() {
        final Function function = new Function(FUNC_CURRENTVALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> destination() {
        final Function function = new Function(FUNC_DESTINATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> origin() {
        final Function function = new Function(FUNC_ORIGIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay() {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> refund() {
        final Function function = new Function(
                FUNC_REFUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ModeratedTransaction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ModeratedTransaction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ModeratedTransaction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ModeratedTransaction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ModeratedTransaction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ModeratedTransaction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ModeratedTransaction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ModeratedTransaction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ModeratedTransaction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _destination, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _destination), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ModeratedTransaction.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ModeratedTransaction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _destination, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _destination), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ModeratedTransaction.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ModeratedTransaction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _destination, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _destination), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ModeratedTransaction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ModeratedTransaction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _destination, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _destination), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ModeratedTransaction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
