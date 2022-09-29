import { Component, OnInit } from '@angular/core';
import {Product} from "../model/Product";
import {ProductService} from "../service/product.service";
import {ActivatedRoute} from "@angular/router";
import {CartService} from "../service/cart.service";
import {ToastrService} from "ngx-toastr";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  username: string
  product: Product

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private toast: ToastrService,
              private title: Title) {
    this.username = sessionStorage.getItem("username");
    this.title.setTitle("Apple - Detail")
  }

  ngOnInit() {
    this.productService.detail(this.activatedRoute.snapshot.paramMap.get("id")).subscribe(
      value => {this.product = value}
    )
  }

  addToCart(id: number) {
    this.cartService.addToCard(id,this.username).subscribe(
      value => {this.toast.success("added new successfully")},
      error => {this.toast.error("can't add to card")}
    )
  }
}
