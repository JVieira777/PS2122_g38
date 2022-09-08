
export  function End_date(){
   
    var today = new Date();

    var date = today.getFullYear()+'-'+(today.getMonth()+3)+'-'+today.getDate();

    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

    var dateTime = date+' '+time;

    var datemili = new Date(dateTime).getTime()
    
    console.log(datemili)
    return datemili
}