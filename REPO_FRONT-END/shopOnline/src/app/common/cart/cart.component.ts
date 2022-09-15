import { Component, OnInit } from '@angular/core';
import {Cart} from "../model/Cart";
import {CartService} from "../service/cart.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  totalPrice =0;
  username: string;
  cart: Cart[]
  sum=0;

  constructor(private cartService: CartService) {
    this.username = sessionStorage.getItem("username");
    console.log(this.username)
  }

  ngOnInit() {
    this.cartService.getCart(this.username).subscribe(
      value => {this.cart = value
        console.log(value)
      },
      error => {},
      () => {
        this.totalPrice = 0;
        for (let i = 0; i < this.cart.length; i++) {
          this.sum=0;
          this.sum = this.cart[i].productItem.price * this.cart[i].quantity;
          this.totalPrice += this.sum;
          console.log(this.totalPrice)
        }
      }
    )
  }


  upQuantity(id) {
    this.cartService.upQuantity(this.username,id).subscribe(value => {},error => {},() => {this.ngOnInit()})
  }

  downQuantity(id) {
    this.cartService.downQuantity(this.username,id).subscribe(value => {},error => {},() => {this.ngOnInit()})
  }

  remove(id) {
    this.cartService.remove(id,this.username).subscribe(value => {},error => {},() => {this.ngOnInit()})
  }
}
