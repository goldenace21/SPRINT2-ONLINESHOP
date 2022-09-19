import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/Product";
import {ActivatedRoute} from "@angular/router";
import {CartService} from "../service/cart.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  products: Product[];
  name="";
  limit=6;
  cate:string
  username:string;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute, private cartService: CartService, private toast: ToastrService) { }

  ngOnInit() {
    this.username = sessionStorage.getItem("username");
    this.cate = this.activatedRoute.snapshot.paramMap.get("cate")
    this.productService.getList(this.name, this.limit, this.cate).subscribe(
      value => {this.products = value
        console.log(value)},
      error => {},
      () => {}
    )
  }

  addToCart(id: number) {
    this.cartService.addToCard(id,this.username).subscribe(
      value => {this.toast.success("added new successfully")},
      error => {this.toast.error("can't add to card")}
    )


  }

  loadMore() {
    this.limit = this.limit + 3;
    this.ngOnInit()
  }
}
