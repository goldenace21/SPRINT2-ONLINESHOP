import { Component, OnInit } from '@angular/core';
import {Product} from "../model/Product";
import {ProductService} from "../service/product.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  product: Product

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.productService.detail(this.activatedRoute.snapshot.paramMap.get("id")).subscribe(
      value => {this.product = value}
    )
  }

}
