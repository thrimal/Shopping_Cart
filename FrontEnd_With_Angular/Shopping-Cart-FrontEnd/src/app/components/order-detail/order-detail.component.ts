import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../services/order-service/order.service";
import {CartService} from "../../services/cart-service/cart.service";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {
  orders: any = [];
  carts: any=[];
  constructor(private orderService: OrderService,private cartService:CartService) { }

  ngOnInit(): void {
    this.getAllOrder();
    this.getAllCarts();
    this.cartService.removeAllCartItems();
  }

  getAllCarts(){
    this.cartService.getAll()
      .subscribe(res=>{
        this.carts=res;
      },
        error => {
         console.log(error);
        }
      )
  }

  getAllOrder() {
    this.orderService.getAll().subscribe(
      data => {
        this.orders = data;
      },
      error => {
        console.log(error);
      });
  }

  removeOrder(order:any) {
    this.orders.map((a:any, index:any)=>{
      if(order.orderId===a.orderId){
        this.orderService.delete(order.orderId)
          .subscribe(res=>{
              alert("Order Removed...");
              this.getAllOrder();
              this.getAllCarts();
            },
            error => {
              console.log(error);
            }
          )
      }
    })

  }
}
