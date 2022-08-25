
export  function End_date(){
    /*var date = new Date()
    date = date.getDate() - (date.getDay() - 1) + 120
    return date*/
    var today = new Date();

    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

    var dateTime = date+' '+time;
}