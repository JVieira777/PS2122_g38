// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

contract ExchangeHolder{

    modifier onlyBy(address _account) {
      require(
         msg.sender == _account,
         "Unauthorized user."
      );
      _;
   }

struct Exchange {
        uint price;
        address payable origin ;
        address payable destination;
        uint end_date;
        bool isPayed;
        bool isCompleted;
    }

address payable owner;

mapping(uint => Exchange) public exchanges;

    constructor(){
        owner = payable(msg.sender);
    }

    function newExchange(uint _id, uint _price, address payable _destination, uint _end_date) public onlyBy(owner){
        exchanges[_id] = Exchange(_price,payable(address(0x0)),_destination,_end_date,false,false);
    }

    function pay(uint _id) public payable {
        require(
           msg.value == exchanges[_id].price && !exchanges[_id].isPayed,
           "Incorrect transaction amount."
        );
        if(exchanges[_id].destination == address(0x0)){
            payable(msg.sender).transfer(msg.value);
        }
        exchanges[_id].origin = payable(msg.sender);
        exchanges[_id].isPayed = true;
    }

    function refund(uint _id) public onlyBy(owner){
        require(
            exchanges[_id].isPayed && !exchanges[_id].isCompleted,
            "No funds to refund."
        );
        exchanges[_id].origin.transfer(exchanges[_id].price);
    }


    function collect(uint _id) public{
        require(
            msg.sender == exchanges[_id].destination,
            "Invalid user."
        );
        require(
            exchanges[_id].isCompleted && exchanges[_id].isPayed,
            "Order not yet completed."
        );

        payable(msg.sender).transfer(exchanges[_id].price);
    }


    function completeOrder(uint _id) public onlyBy(owner){
        require(
            exchanges[_id].isPayed,
            "Exchange has not been payed yet."
        );
        exchanges[_id].isCompleted = true;
    }
}