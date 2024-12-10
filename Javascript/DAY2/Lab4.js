var username="Admin";
var password="viewer";
console.log("--------------------------")
if(username=="Admin" && password=="Admin"){
    console.log("Welcome" + username +", Logged in as Admin");
}
else if(username=="User" && password=="1234"){
    console.log("Welcome"+ username+ ",Logged in as User");
}
else if(username=="Admin" && password=="editor"){
    console.log("Welcome"+ username+ ",Logged in as Editor");
}
else if(username=="Admin" && password=="viewer"){
    console.log("Welcome"+ username+ ",Logged in as Viewer");
}
else{
    console.log("Invalid credentials");
}
console.log("------------------------")