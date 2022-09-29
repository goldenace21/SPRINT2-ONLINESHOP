import { Component, OnInit } from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {ReceiptService} from "../service/receipt.service";
import {Receipt} from "../model/Receipt";
import {Cart} from "../model/Cart";
import {CartService} from "../service/cart.service";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  receipts: Receipt[];
  carts: Cart[]
  username: string
  total = 0;

  constructor(private toast: ToastrService,private receiptService: ReceiptService, private cartService: CartService, private router: Router, private title: Title) {
    this.title.setTitle("Apple - Transaction")
  }

  ngOnInit() {
    this.username = sessionStorage.getItem("username")
    this.receiptService.getList(this.username, 5).subscribe(value => {this.receipts = value;
      console.log(this.receipts)});
  }


  showDetail(id: number,totalPrice :number) {
    this.cartService.getCartByReceipt(id).subscribe(
      value => {this.carts = value;
        console.log(this.carts)
       this.total = totalPrice
        console.log(this.total)
        document.getElementById("modal").click()
      }
    )
  }
}
