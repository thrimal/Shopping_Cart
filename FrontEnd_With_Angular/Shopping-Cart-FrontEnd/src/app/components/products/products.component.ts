import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../services/product-service/product.service";
import {CategoryService} from "../../services/category-service/category.service";
import {CartService} from "../../services/cart-service/cart.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  products: any=[];
  productId: any;
  description: any;
  categoryId: any;
  price: any;
  qtyOnHand: any;
  categoryIds: any=[];
  categories: any=[];
  categoryId2: any;
  cDescription: any;

  constructor(
    private productService:ProductService,
    private categoryService:CategoryService,
    private cartService : CartService
    ) { }

  ngOnInit(): void {
    this.cartService.removeAllCartItems();
    this.getAllProduct();
    this.getAllCategory();
  }

  saveProduct() {
    const data:any = {
      productId: this.productId,
      description: this.description,
      categoryId: this.categoryId,
      price:this.price,
      qtyOnHand:this.qtyOnHand,
    }
    let formData=new FormData();
    formData.append('form',data);
    this.productService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllProduct();
          alert("Product Added Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  updateProduct() {
    const data = {
      productId: this.productId,
      description: this.description,
      categoryId: this.categoryId,
      price:this.price,
      qtyOnHand:this.qtyOnHand,
    }
    this.productService.update(this.productId, data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllProduct();
          alert("Product Update Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  getAllProduct() {
    this.productService.getAll().subscribe(
      data => {
        this.products = data;
      },
      error => {
        console.log(error);
      });
  }

  getProduct() {
    this.productService.get(this.productId)
      .subscribe(response => {
        if (response != null) {
          this.description=response.description;
          this.categoryId = response.categoryId;
          this.price = response.price;
          this.qtyOnHand =response.qtyOnHand;
          console.log(response);
        }
      }, error => {
        console.log(error);
      })
  }

  deleteProduct() {
    this.productService.delete(this.productId)
      .subscribe(response => {
        this.getAllProduct();
        alert("Product Removed...");
      }, error => {
        console.log(error)
      })
  }

  valueChange(event: any) {
    this.categoryId=event.target.value;
  }

  getAllCategory() {
    this.categoryService.getAll().subscribe(
      data => {
        this.categoryIds = data;
        this.categories=data;
      },
      error => {
        console.log(error);
      });
  }

  saveCategory() {
    const data:any={
      categoryId:this.categoryId2,
      description:this.cDescription,
    }
    this.categoryService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllCategory();
          alert("Category Added Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  updateCategory() {
    const data = {
      categoryId: this.categoryId2,
      description: this.cDescription,
    }
    this.categoryService.update(this.categoryId2, data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllProduct();
          alert("Category Update Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  getCategory() {
    this.categoryService.get(this.categoryId2)
      .subscribe(response => {
        if (response != null) {
          this.categoryId2=response.categoryId;
          this.cDescription = response.description;
          console.log(response);
        }
      }, error => {
        console.log(error);
      })
  }

  deleteCategory() {
    this.categoryService.delete(this.categoryId2)
      .subscribe(response => {
        this.getAllCategory();
        alert("Category Removed...");
      }, error => {
        console.log(error);
      })
  }
}
