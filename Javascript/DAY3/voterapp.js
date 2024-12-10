let name = prompt("Enter your name:");
let age = prompt("Enter your age:");
age = Number(age);
if (age >= 18) {
  alert(`Hello ${name}, you are eligible to vote.`);
} else {
  alert(`Hello ${name}, you are not eligible to vote yet.`);
}
