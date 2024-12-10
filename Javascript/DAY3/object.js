var product = {
    product_id: 101,
    product_name: "Samsung S10",
    guarantee: "6 months",
    specifications: {
        storage: "250GB",
        camera : "50MP",
        display : "6 inch"
    },
    colors : ['starry black','green','shiny blue','grey']

}
console.log(product.product_name);
console.log(product.specifications.camera);
console.log(product.colors[2]);
product.price = 78000;
product.specifications.battery = "6000MH";
product.product_id = 120;
delete product.guarantee;
console.log(product);

