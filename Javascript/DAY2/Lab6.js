const start = 10;
const end = 20;

console.log("EVEN NO.:");
for (let i = start; i <= end; i++) {
  if (i % 2 === 0) {
    console.log(i);
  }
}

console.log("ODD NO.:");
let i = start;
while (i <= end) {
  if (i % 2 !== 0) {
    console.log(i); 
  }
  i++;
}
