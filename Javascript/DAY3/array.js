let products = ["Laptop", "Tablet", "Headphones", "Smartwatch"];
console.log("Initial Products List:", products);
console.log("First Product:", products[0], "| Last Product:", products[products.length - 1]);
products.push("Camera");
console.log("After Adding 'Camera':", products);
products.splice(2, 0, "Monitor");
console.log("\n After Inserting 'Monitor' at index 2:", products);
let removedProduct = products.splice(3, 1);
console.log(`\n After Removing '${removedProduct}' at index 3:`, products);

// Sort the array in ascending order
products.sort();
console.log("\n After Sorting:", products);

// Reverse the sorted array
products.reverse();
console.log("\n After Reverse Sorting:", products);
let numbers = [42, 19, 1, 56, 12];
console.log("Initial Numbers Array:", numbers);

// Sort numbers in ascending order
numbers.sort((a, b) => a - b);
console.log("After Sorting Numbers (Ascending):", numbers);

// Sort numbers in descending order
numbers.sort((a, b) => b - a);
console.log("After Sorting Numbers (Descending):", numbers);