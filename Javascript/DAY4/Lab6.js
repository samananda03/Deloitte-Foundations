const marks = [60,80,90,34,70,8]
let updatedMarks = marks.map((mark)=> mark+2)
console.log("--------------------------");
console.log(updatedMarks);
console.log("--------------------------------");
updatedMarks = marks.map((mark)=>mark+2).map((mark)=>mark-1)
console.log(updatedMarks);
console.log("-----------------------------");

let num = [1,2,3,4]
const total = num.reduce(function(acc, curvalue){
    console.log("accumulator value ",acc,"current value",acc);
    return acc+curvalue
},0)

console.log(total);
const shoppingCart = [
    {
        itemName: 'JS Course',
        price:3000
    },
    {
        itemName: 'Java Course',
        price:6000
    },
    {
        itemName: 'Python Course',
        price:2000
    },
    {
        itemName: 'ReactJS Course',
        price:3500
    }
   
]

var result = shoppingCart.reduce((acc,item)=> acc+item.price,0)

console.log(result);