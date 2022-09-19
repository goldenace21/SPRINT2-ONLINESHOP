import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {CartService} from "../service/cart.service";
import {Cart} from "../model/Cart";
import {AuthGuardAdminCustomerService} from "../service/guard/auth-guard-admin-customer.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  role: string;
  checkCart = false;
  username: string;
  private subscriptionName: Subscription;

  constructor(
    // private logout: LogoutService,
    // private toastr: ToastrService,
    private router: Router,
    private cartService: CartService,
    private authGuardAdminCustomer: AuthGuardAdminCustomerService) {
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem("username");
    this.cartService.getCart(this.username).subscribe(
      value => {
      if(value.length != 0) {
        this.checkCart = true
      } else {
        this.checkCart = false
      }}
    )
  }

  readLocalStorage(key: string): string {
    return localStorage.getItem(key);
  }
}
