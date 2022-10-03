import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/Product";
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../service/cart.service";
import {ToastrService} from "ngx-toastr";
import {Title} from "@angular/platform-browser";
import {ReloadService} from "../service/reload.service";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  products: Product[];
  name: string="";
  limit=6;
  username:string;
  cate :string;
  itemDelete: Product
  roles: string;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private toast: ToastrService,
              private title: Title,
              private reload: ReloadService,
              private router: Router) {
    this.title.setTitle("Apple - Products");
    this.reload.currentMessage.subscribe(
      value =>
      {
        if (value !== 'none'){
          this.cate = value;
        }
        this.ngOnInit()
      }
    );

  }

  ngOnInit() {
    console.log(this.cate)
    this.username = sessionStorage.getItem("username");
    this.roles = sessionStorage.getItem("roles");
    this.getList();
  }

  getList() {
    setTimeout(()=>{
      this.cate = this.activatedRoute.snapshot.paramMap.get("cate")
      this.productService.getList(this.name, this.limit,this.cate).subscribe(
        value => {this.products = value;
          window.scroll(0,0)
        }
      )
    }, 50);
  }

  addToCart(id: number) {
    if (sessionStorage.getItem("username")==null) {
      this.router.navigateByUrl("/login")
      return
    }
    this.cartService.addToCard(id,this.username).subscribe(
      value => {this.toast.success("added new successfully")},
      error => {this.toast.error("can't add to card")}
    )
  }

  loadMore() {
    this.limit = this.limit + 3;
    this.ngOnInit()
  }

  confirm(id: number) {
    this.productService.detail(id + "").subscribe(
      value => {this.itemDelete = value
        document.getElementById("modal").click()
      }
    )
  }

  delete(id: number) {
    this.productService.delete(id + "").subscribe(
      value => {this.toast.success("delete successfully");
        document.getElementById("modal").click()
        this.ngOnInit()},
    )
  }

  update(id: number) {
    this.router.navigateByUrl(`/create/${id}`);
  }

  search() {
    this.productService.getList(this.name, this.limit, this.cate).subscribe(
      value => {this.products = value
      this.ngOnInit()}
    )
  }

  listItem(cate:number) {
    this.router.navigateByUrl("list/" + cate)
    this.ngOnInit()

  }
}
