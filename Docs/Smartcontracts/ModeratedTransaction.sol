// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

contract ModeratedTransaction {
    //this contract is used to be hable to refund
    address public owner;
    address payable public destination;
    address payable public origin;

    uint256 public price;
    uint256 public currentValue;

    bool isPayed;

    modifier onlyBy(address _account) {
        require(msg.sender == _account, "Sender not authorized.");
        _;
    }

    constructor(address payable _destination, uint256 _price) {
        owner = msg.sender;
        destination = _destination;
        price = _price;
    }

    function pay() public payable {
        if (!isPayed) {
            if (msg.value >= price) {
                isPayed = true;
                origin = payable(msg.sender);

                if (msg.value > price) {
                    payable(msg.sender).transfer(msg.value - price);
                }
            }
        }
    }

    function refund() public payable onlyBy(owner) {
        if (isPayed) {
            origin.transfer(price);
        }
    }

    function completePayment() public payable onlyBy(owner) {
        if (isPayed) {
            destination.transfer(price);
        }
    }
}

