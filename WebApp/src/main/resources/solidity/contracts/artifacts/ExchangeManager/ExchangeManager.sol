// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

contract ExchangeManager{

    modifier onlyBy(address _account) {
      require(
         msg.sender == _account,
         "Unauthorized user."
      );
      _;
   }

    event exchangeCreation(uint _id, uint price, address _destination, uint _end_date);

    struct Exchange {
            uint price;
            address payable origin ;
            address payable destination;
            uint end_date;
            bool isPayed;
            bool refundable;
            bool completed;
    }

    address payable owner;

    uint256 counter = 0;

    mapping(uint => Exchange) public exchanges;

    constructor(){
        owner = payable(msg.sender);
    }

    function newExchange(uint _price, address payable _destination, uint _end_date) public onlyBy(owner){
        counter = counter + 1;
        exchanges[counter] = Exchange( _price, payable( address(0x0) ), _destination, _end_date, false, false,false);
        emit exchangeCreation(counter, _price, _destination, _end_date);
    }

    function pay(uint _id) public payable {
        require(
           msg.value == exchanges[_id].price && !exchanges[_id].isPayed,
           "Incorrect transaction amount."
        );
        require(
           exchanges[_id].destination != address(0x0),
           "Invalid Exchange id"
        );
        exchanges[_id].origin = payable(msg.sender);
        exchanges[_id].isPayed = true;
    }

    function refund(uint _id) public onlyBy(owner){
        require(
           exchanges[_id].isPayed && !exchanges[_id].completed,
           "No funds to return! the exchange might already have been completed or not payed at all."
        );
        exchanges[_id].refundable = true;
    }

    function collectRefund(uint _id) public onlyBy(exchanges[_id].origin){
        require(
            exchanges[_id].isPayed && exchanges[_id].refundable && !exchanges[_id].completed,
            "Invalid request! (No funds to refund / exchange refund not validated )"
        );
        exchanges[_id].origin.transfer(exchanges[_id].price);
        exchanges[_id].completed = true;
    }

    function collect(uint _id) public onlyBy(exchanges[_id].destination){
        require(
            block.timestamp >= exchanges[_id].end_date && exchanges[_id].isPayed && !exchanges[_id].completed && !exchanges[_id].refundable,
            "Too soon to collect or funds already collected"
        );
        payable(msg.sender).transfer(exchanges[_id].price);
        exchanges[_id].completed = true;
    }
}