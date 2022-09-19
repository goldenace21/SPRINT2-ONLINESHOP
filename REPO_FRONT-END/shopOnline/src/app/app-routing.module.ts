import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomePageComponent} from "./common/home-page/home-page.component";
import {DetailComponent} from "./common/detail/detail.component";
import {ListComponent} from "./common/list/list.component";
import {LoginComponent} from "./common/login/login.component";
import {RegisterComponent} from "./common/register/register.component";
import {CartComponent} from "./common/cart/cart.component";
import {Error401Component} from "./common/error/error401/error401.component";
import {AuthGuardAdminCustomerService} from "./common/service/guard/auth-guard-admin-customer.service";


const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "home"},
  {path: "home", component: HomePageComponent},
  {path: "register", component: RegisterComponent},
  {path: "list/:cate", component: ListComponent},
  {path: "login", component: LoginComponent},
  {path: "detail/:id", component: DetailComponent},
  {path: "cart", component: CartComponent, canActivate: [AuthGuardAdminCustomerService]},
  {path: "401", component: Error401Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
