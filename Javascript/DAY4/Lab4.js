let fetchData = new Promise((resolve,reject)=>{
    let dataAvailable = false;
    if(dataAvailable){
        resolve("data fetched succesfully")
    }
    else{
        reject("data not found");
    }
})

fetchData
.then((msg)=> {
    console.log("Success: ",msg);
})
.catch((error)=> console.log("ERROR: ", error))