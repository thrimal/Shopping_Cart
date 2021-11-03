import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";

const baseUrl = 'http://localhost:8080/api/v1/cart'
@Injectable({
  providedIn: 'root'
})
export class CartService {
  public cartItemList : any=[];
  public allCarts=new BehaviorSubject<any>([]);

  constructor(private http:HttpClient) { }

  getAll() {
    return this.http.get<any>(baseUrl);
  }

  get(id: any) {
    return this.http.get<any>(`${baseUrl}/${id}`);
  }

  create(data: any) {
    return this.http.post(baseUrl,data);
  }

  update(id: any, data: any) {
    return this.http.put<any>(`${baseUrl}`, data);
  }

  delete(id: any) {
    return this.http.delete(`${baseUrl}/?id=${id}`);
  }

  addCount(data:any){
    this.cartItemList.push(data);
    this.count();
  }

  count(){
    this.allCarts.next(this.cartItemList)
  }

  removeCartItem(cart: any){
    this.cartItemList.map((a:any, index:any)=>{
      if(cart.lineItemId === a.lineItemId){
        this.cartItemList.splice(index,1);
      }
    })
    this.count();
  }

  removeAllCartItems(){
    this.cartItemList=[];
    this.count();
  }

  getAllCounts(){
    console.log(this.allCarts);
    return this.allCarts.asObservable();
  }
}
