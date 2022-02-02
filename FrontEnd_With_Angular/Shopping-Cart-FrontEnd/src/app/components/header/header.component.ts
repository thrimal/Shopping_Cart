import { Component, OnInit } from '@angular/core';
import {CartService} from "../../services/cart-service/cart.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  totalCarts: number=0;

  constructor(private cartService:CartService) { }

  ngOnInit(): void {
    this.cartService.getAllCounts()
      .subscribe((res:any)=>{
        this.totalCarts=res.length;
      })
  }

}
