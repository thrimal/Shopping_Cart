import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CustomerService} from "../../services/customer-service/customer.service";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  customerForm= new FormGroup({
    id:new FormControl(''),
    name:new FormControl(''),
    address:new FormControl(''),
    salary:new FormControl()
  });
  loading= true;

  constructor(private customerService :CustomerService) { }

  ngOnInit(): void {
  }

  register() {
    this.loading=true;
    const data:any={
      customerId:this.customerForm.get('id')?.value.toString().trim(),
      customerName:this.customerForm.get('name')?.value.toString().trim(),
      customerAddress:this.customerForm.get('address')?.value.toString().trim(),
      customerSalary:parseFloat(this.customerForm.get('salary')?.value.toString().trim()),
    }
    console.log(data);
    this.customerService.create2(data)
      .subscribe((res:any)=>{
        alert("Customer Save Success...");
        console.log(res);
      },
        error => {
          console.log(error);
        }
        )
  }

}
