package pt.isel.WebApp.lib.services.blockchain.wrappers;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
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
public class ExchangeManager extends Contract {
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061120d806100656000396000f3fe6080604052600436106100555760003560e01c8063278ecde11461005a5780632839fc29146100835780636777e75e146100c6578063c290d691146100ef578063c29a44cf1461010b578063ce3f865f14610134575b600080fd5b34801561006657600080fd5b50610081600480360381019061007c9190610bda565b61015d565b005b34801561008f57600080fd5b506100aa60048036038101906100a59190610bda565b6102af565b6040516100bd9796959493929190610e28565b60405180910390f35b3480156100d257600080fd5b506100ed60048036038101906100e89190610c07565b610358565b005b61010960048036038101906101049190610bda565b6105c3565b005b34801561011757600080fd5b50610132600480360381019061012d9190610bda565b610775565b005b34801561014057600080fd5b5061015b60048036038101906101569190610bda565b61099c565b005b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146101ec576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101e390610dc8565b60405180910390fd5b6002600083815260200190815260200160002060040160009054906101000a900460ff16801561023d57506002600083815260200190815260200160002060040160029054906101000a900460ff16155b61027c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027390610d68565b60405180910390fd5b60016002600084815260200190815260200160002060040160016101000a81548160ff0219169083151502179055505050565b60026020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060040160029054906101000a900460ff16905087565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146103e7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103de90610dc8565b60405180910390fd5b600180546103f59190610eed565b6001819055506040518060e00160405280858152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff168152602001838152602001600015158152602001600015158152602001600015158152506002600060015481526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506060820151816003015560808201518160040160006101000a81548160ff02191690831515021790555060a08201518160040160016101000a81548160ff02191690831515021790555060c08201518160040160026101000a81548160ff0219169083151502179055509050507f9fe62169449c1d90424003ecc2108325ff40419393435b11b90ae3126c165e2b6001548585856040516105b59493929190610e97565b60405180910390a150505050565b60026000828152602001908152602001600020600001543414801561060957506002600082815260200190815260200160002060040160009054906101000a900460ff16155b610648576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161063f90610e08565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156106ee576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106e590610da8565b60405180910390fd5b336002600083815260200190815260200160002060010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060016002600083815260200190815260200160002060040160006101000a81548160ff02191690831515021790555050565b6002600082815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461081a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161081190610dc8565b60405180910390fd5b6002600083815260200190815260200160002060040160009054906101000a900460ff16801561086a57506002600083815260200190815260200160002060040160019054906101000a900460ff165b801561089757506002600083815260200190815260200160002060040160029054906101000a900460ff16155b6108d6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108cd90610de8565b60405180910390fd5b6002600083815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60026000858152602001908152602001600020600001549081150290604051600060405180830381858888f19350505050158015610968573d6000803e3d6000fd5b5060016002600084815260200190815260200160002060040160026101000a81548160ff0219169083151502179055505050565b6002600082815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a41576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a3890610dc8565b60405180910390fd5b60026000838152602001908152602001600020600301544210158015610a8757506002600083815260200190815260200160002060040160009054906101000a900460ff165b8015610ab457506002600083815260200190815260200160002060040160029054906101000a900460ff16155b8015610ae157506002600083815260200190815260200160002060040160019054906101000a900460ff16155b610b20576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b1790610d88565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc60026000858152602001908152602001600020600001549081150290604051600060405180830381858888f19350505050158015610b7c573d6000803e3d6000fd5b5060016002600084815260200190815260200160002060040160026101000a81548160ff0219169083151502179055505050565b600081359050610bbf816111a9565b92915050565b600081359050610bd4816111c0565b92915050565b600060208284031215610bf057610bef610ff0565b5b6000610bfe84828501610bc5565b91505092915050565b600080600060608486031215610c2057610c1f610ff0565b5b6000610c2e86828701610bc5565b9350506020610c3f86828701610bb0565b9250506040610c5086828701610bc5565b9150509250925092565b610c6381610f8b565b82525050565b610c7281610f43565b82525050565b610c8181610f55565b82525050565b6000610c94605783610edc565b9150610c9f82610ff5565b606082019050919050565b6000610cb7602e83610edc565b9150610cc28261106a565b604082019050919050565b6000610cda601383610edc565b9150610ce5826110b9565b602082019050919050565b6000610cfd601283610edc565b9150610d08826110e2565b602082019050919050565b6000610d20604683610edc565b9150610d2b8261110b565b606082019050919050565b6000610d43601d83610edc565b9150610d4e82611180565b602082019050919050565b610d6281610f81565b82525050565b60006020820190508181036000830152610d8181610c87565b9050919050565b60006020820190508181036000830152610da181610caa565b9050919050565b60006020820190508181036000830152610dc181610ccd565b9050919050565b60006020820190508181036000830152610de181610cf0565b9050919050565b60006020820190508181036000830152610e0181610d13565b9050919050565b60006020820190508181036000830152610e2181610d36565b9050919050565b600060e082019050610e3d600083018a610d59565b610e4a6020830189610c69565b610e576040830188610c69565b610e646060830187610d59565b610e716080830186610c78565b610e7e60a0830185610c78565b610e8b60c0830184610c78565b98975050505050505050565b6000608082019050610eac6000830187610d59565b610eb96020830186610d59565b610ec66040830185610c5a565b610ed36060830184610d59565b95945050505050565b600082825260208201905092915050565b6000610ef882610f81565b9150610f0383610f81565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610f3857610f37610fc1565b5b828201905092915050565b6000610f4e82610f61565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000610f9682610f9d565b9050919050565b6000610fa882610faf565b9050919050565b6000610fba82610f61565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600080fd5b7f4e6f2066756e647320746f2072657475726e21207468652065786368616e676560008201527f206d6967687420616c72656164792068617665206265656e20636f6d706c657460208201527f6564206f72206e6f7420706179656420617420616c6c2e000000000000000000604082015250565b7f546f6f20736f6f6e20746f20636f6c6c656374206f722066756e647320616c7260008201527f6561647920636f6c6c6563746564000000000000000000000000000000000000602082015250565b7f496e76616c69642045786368616e676520696400000000000000000000000000600082015250565b7f556e617574686f72697a656420757365722e0000000000000000000000000000600082015250565b7f496e76616c696420726571756573742120284e6f2066756e647320746f20726560008201527f66756e64202f2065786368616e676520726566756e64206e6f742076616c696460208201527f6174656420290000000000000000000000000000000000000000000000000000604082015250565b7f496e636f7272656374207472616e73616374696f6e20616d6f756e742e000000600082015250565b6111b281610f43565b81146111bd57600080fd5b50565b6111c981610f81565b81146111d457600080fd5b5056fea2646970667358221220cbe1f04e02ab8324639d70008d95e0d89452684863c7c84f50da06671eb7554064736f6c63430008070033";

    public static final String FUNC_COLLECT = "collect";

    public static final String FUNC_COLLECTREFUND = "collectRefund";

    public static final String FUNC_EXCHANGES = "exchanges";

    public static final String FUNC_NEWEXCHANGE = "newExchange";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_REFUND = "refund";

    public static final Event EXCHANGECREATION_EVENT = new Event("exchangeCreation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ExchangeManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ExchangeManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ExchangeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ExchangeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ExchangeCreationEventResponse> getExchangeCreationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(EXCHANGECREATION_EVENT, transactionReceipt);
        ArrayList<ExchangeCreationEventResponse> responses = new ArrayList<ExchangeCreationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ExchangeCreationEventResponse typedResponse = new ExchangeCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.exchange_id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.destination = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.end_date = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ExchangeCreationEventResponse> exchangeCreationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ExchangeCreationEventResponse>() {
            @Override
            public ExchangeCreationEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(EXCHANGECREATION_EVENT, log);
                ExchangeCreationEventResponse typedResponse = new ExchangeCreationEventResponse();
                typedResponse.log = log;
                typedResponse.exchange_id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.destination = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.end_date = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ExchangeCreationEventResponse> exchangeCreationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EXCHANGECREATION_EVENT));
        return exchangeCreationEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> collect(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECT, 
                Arrays.<Type>asList(new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> collectRefund(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECTREFUND, 
                Arrays.<Type>asList(new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>> exchanges(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EXCHANGES, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> newExchange(BigInteger _price, String _destination, BigInteger _end_date) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NEWEXCHANGE, 
                Arrays.<Type>asList(new Uint256(_price),
                new Address(160, _destination),
                new Uint256(_end_date)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> refund(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REFUND, 
                Arrays.<Type>asList(new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ExchangeManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExchangeManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ExchangeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExchangeManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ExchangeManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ExchangeManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ExchangeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ExchangeManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ExchangeManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ExchangeManager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ExchangeManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ExchangeManager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ExchangeManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExchangeManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ExchangeManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExchangeManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ExchangeCreationEventResponse extends BaseEventResponse {
        public BigInteger exchange_id;

        public BigInteger price;

        public String destination;

        public BigInteger end_date;
    }
}
