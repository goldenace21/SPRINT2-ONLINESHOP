import { Component, OnInit } from '@angular/core';
import {Cart} from "../model/Cart";
import {CartService} from "../service/cart.service";
import {paypal, render} from "creditcardpayments/creditCardPayments";
import {ToastrService} from "ngx-toastr";
import {ReceiptService} from "../service/receipt.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  totalPrice = 0;
  username: string;
  cart: Cart[]
  sum=0;

  constructor(private cartService: CartService, private toast: ToastrService, private receiptService: ReceiptService) {
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

  pay() {
    if (this.totalPrice == 0) {
      this.toast.warning("No products in the basket")
      return
    }
    document.getElementById("paypal-button-container").innerHTML = '<div id="paypal-button"></div>';
    render({
      id: '#paypal-button',
      value: this.totalPrice.toFixed(2),
      currency: 'USD',
      onApprove: (detail) => {
        this.receiptService.receipt(this.username).subscribe(
          value => {
            this.toast.success("Payment is Successful")
            document.getElementById("close-modal").click()
            this.ngOnInit()
          }
        )
      }});
    document.getElementById("modal").click();
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
