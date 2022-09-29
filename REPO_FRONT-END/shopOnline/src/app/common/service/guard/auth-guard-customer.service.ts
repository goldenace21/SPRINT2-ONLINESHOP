import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardCustomerService implements CanActivate{

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const roles = sessionStorage.getItem("roles");;
    if (roles === 'CUSTOMER') {
      return true;
    } else {
      this.router.navigateByUrl('/login');
    }
  }
}
