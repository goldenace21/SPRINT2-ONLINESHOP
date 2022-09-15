import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  role: string;
  username: string;
  private subscriptionName: Subscription;

  constructor(
    // private logout: LogoutService,
    // private toastr: ToastrService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  readLocalStorage(key: string): string {
    return localStorage.getItem(key);
  }

}
