import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./components/customer/customer.component";
import {ProductsComponent} from "./components/products/products.component";
import {PlaceOrderComponent} from "./components/placeOrder/placeOrder.component";
import {OrderDetailComponent} from "./components/order-detail/order-detail.component";
import {TestComponent} from "./components/test/test.component";

const routes: Routes = [
  {path:'',redirectTo:"customer",pathMatch:"full"},
  {path:'customer',component:CustomerComponent},
  {path:'products',component:ProductsComponent},
  {path:'placeOrder',component:PlaceOrderComponent},
  {path:'orderDetail',component:OrderDetailComponent},
  {path:'test',component:TestComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
