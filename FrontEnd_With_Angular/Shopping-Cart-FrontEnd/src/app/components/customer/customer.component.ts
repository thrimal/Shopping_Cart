import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer-service/customer.service";
import {CartService} from "../../services/cart-service/cart.service";

export interface Customer {
  customerId: string,
  customerName: string,
  customerAddress: string,
  customerSalary:string,
}

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})

export class CustomerComponent implements OnInit {

  id: string = '';
  name: string = '';
  address: string = '';
  salary: string='';
  customers: Customer[] = [];
  form: any;

  constructor(private customerService: CustomerService,private cartService:CartService) {
  }

  ngOnInit(): void {
    this.getAllCustomer();
    this.cartService.removeAllCartItems();
  }

  saveCustomer() {
    const data:any = {
      customerId: this.id,
      customerName: this.name,
      customerAddress: this.address,
      customerSalary:this.salary,
    }
    let formData=new FormData();
    formData.append('form',data);
    this.customerService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllCustomer();
          alert("Customer Added Successfully...");
        },
        error => {
          console.log(error);
        });
  }

  updateCustomer() {
    const data = {
      customerId:this.id,
      customerName: this.name,
      customerAddress: this.address,
      customerSalary: this.salary,
    }
    this.customerService.update(this.id, data)
      .subscribe(
        response => {
          console.log(response);
          this.getAllCustomer();
          alert("Customer Update Successfully...");
        },
        error => {
          console.log(error);
        });
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

  getCustomer() {
    this.customerService.get(this.id)
      .subscribe(response => {
        if (response != null) {
          this.id = response.customerId;
          this.name = response.customerName;
          this.address = response.customerAddress;
          this.salary =response.customerSalary;
          console.log(response);
        }
      }, error => {
        console.log(error);
      })
  }

  deleteCustomer() {
    this.customerService.delete(this.id)
      .subscribe(response => {
        this.getAllCustomer();
        alert("Customer Removed...");
      }, error => {
        console.log(error);
      })
  }
}






























