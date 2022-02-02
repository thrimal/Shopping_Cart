import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomerComponent} from "./components/customer/customer.component";
import { ProductsComponent } from './components/products/products.component';
import { PlaceOrderComponent } from './components/placeOrder/placeOrder.component';
import {CookieModule} from "ngx-cookie";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { OrderDetailComponent } from './components/order-detail/order-detail.component';
import {CommonModule} from "@angular/common";
import { HeaderComponent } from './components/header/header.component';
import { TestComponent } from './components/test/test.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    ProductsComponent,
    PlaceOrderComponent,
    OrderDetailComponent,
    HeaderComponent,
    TestComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CookieModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
