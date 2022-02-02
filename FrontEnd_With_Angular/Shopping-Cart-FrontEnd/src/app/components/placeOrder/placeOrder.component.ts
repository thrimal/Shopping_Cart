import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../services/order-service/order.service";
import {CustomerService} from "../../services/customer-service/customer.service";
import {ProductService} from "../../services/product-service/product.service";
import {CartService} from "../../services/cart-service/cart.service";

@Component({
  selector: 'app-orders',
  templateUrl: './placeOrder.component.html',
  styleUrls: ['./placeOrder.component.scss']
})
export class PlaceOrderComponent implements OnInit {
  orderId: any = '';
  cmbCustomerId: any = '';
  date: any = '';
  status: any = 'Available';
  total: any;
  customers: any = [];
  orders: any = [];
  customerId: any;
  productId: any;
  description: any;
  categoryId: any;
  price: any;
  qtyOnHand: any;
  productIds: any = [];
  tblCart: any = [];
  subTotal: any = 0;
  index1: any = 1;
  index2: any = 1;
  qty: any=1;
  cartId:any;

  constructor(
    private orderService: OrderService,
    private customerService: CustomerService,
    private productService: ProductService,
    private cartService: CartService,
  ) {
  }

  ngOnInit(): void {
    this.cartService.removeAllCartItems();
    this.getAllOrder();
    this.getAllCustomer();
    this.getAllProducts();
    this.calcSubTotal();
    this.index1=1;
  }

  saveOrder() {
    const data: any = {
      orderId: this.orderId,
      customerId: this.customerId,
      date: this.date,
      status: this.status,
      total: this.subTotal,
      lineItemDTOS: this.tblCart
    }
    let formData = new FormData();
    formData.append('form', data);
    this.orderService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllOrder();
          alert("Order Added Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  updateOrder() {
    const data = {
      orderId: this.orderId,
      customerId: this.customerId,
      date: this.date,
      status: this.status,
      total: this.total,
    }
    this.orderService.update(this.orderId, data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllOrder();
          alert("Order Update Successfully...");
        },
        error => {
          console.log(error);
        });
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

  getOrder() {
    this.orderService.get(this.orderId)
      .subscribe(response => {
        if (response != null) {
          this.cmbCustomerId = response.customerId;
          this.date = response.date;
          this.status = response.status;
          this.total = response.total
          console.log(response);
        }
      }, error => {
        console.log(error)
      })
  }

  deleteOrder() {
    this.orderService.delete(this.orderId)
      .subscribe(response => {
        this.getAllOrder();
        alert("Customer Removed...");
      }, error => {
        console.log(error)
      })
  }

  getAllCustomer() {
    this.customerService.getAll().subscribe(
      data => {
        this.customers = data;
      },
      error => {
        console.log(error);
      });
  }

  saveCart() {
    const data = {
      cartId: this.cartId,
      customerId: this.customerId,
      lineItemDTOS: this.tblCart,
    }
    this.cartService.create(data)
      .subscribe(res => {
          console.log(res);
        },
        error => {
          console.log(error);
        }
      )
  }

  valueChangeCustomerId(event: any) {
    this.customerId = event.target.value;
  }

  valueChangeProductId(event: any) {
    this.productId = event.target.value;
    this.getProduct();
  }

  getAllProducts() {
    this.productService.getAll()
      .subscribe(res => {
        this.productIds = res;
      })
  }

  getProduct() {
    this.productService.get(this.productId).subscribe(res => {
      if (res != null) {
        this.description = res.description;
        this.categoryId = res.categoryId;
        this.price = res.price;
        this.qtyOnHand = res.qtyOnHand;
        console.log(res);
      }
    })
  }

  addToTable() {
    let data: any = {
      lineItemId: this.index1,
      cartId: this.cartId,
      customerId: this.customerId,
      orderId: this.orderId,
      itemCode: this.productId,
      price: this.price,
      quantity: this.qty,
      total: this.qty * this.price,
    }
    this.itemAddOrUpdate(data);
    console.log(this.tblCart);
  }

  itemAddOrUpdate(data:any){
    this.tblCart.forEach((res:any)=>{
      if(data.itemCode == res.itemCode){
        res.quantity += data.quantity;
        res.total = res.quantity*res.price;
        this.calcSubTotal();
        this.tblCart.put(res);
        return;
      }
    })
    this.tblCart.push(data);
    this.cartService.addCount(data);
    this.calcSubTotal();
    this.index1++;
  }

  calcSubTotal() {
    this.subTotal = 0;
    this.tblCart.forEach((res: any) => {
      this.subTotal += res.total;
    })
  }

  placeOrder() {
    this.saveOrder();
    this.saveCart();
    this.cartService.removeAllCartItems();
    this.tblCart=[];
    this.index1=1;
  }

  removeItem(p: any) {
    const index = this.tblCart.indexOf(p);
    this.tblCart.splice(index, 1);
    this.calcSubTotal();
    this.cartService.removeCartItem(p);
  }
}
