let time=20;
let message;
switch(true){
    case (time >=6 && time<11):
        message="Good Morning"
        break;
    case (time >=11 && time<14):
        message=" Good Afternoon"
        break;
    case (time >=15 && time<19):
        message = "Good Evening"
        break;
    case (time >=19 && time<24):
        message = "Good Night"
        break;
}

console.log(message);