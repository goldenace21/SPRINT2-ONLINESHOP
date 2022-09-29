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
import {ProfileComponent} from "./common/profile/profile.component";
import {TransactionComponent} from "./common/transaction/transaction.component";
import {CreateComponent} from "./common/create/create.component";
import {AuthGuardAdminService} from "./common/service/guard/auth-guard-admin.service";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "home"},
  {path: "home", component: HomePageComponent},
  {path: "register", component: RegisterComponent},
  {path: "list/:cate", component: ListComponent},
  {path: "login", component: LoginComponent},
  {path: "detail/:id", component: DetailComponent},
  {path: "cart", component: CartComponent, canActivate: [AuthGuardAdminCustomerService]},
  {path: "401", component: Error401Component},
  {path: "create/:id", component: CreateComponent, canActivate: [AuthGuardAdminService]},
  {path: "profile", component: ProfileComponent, canActivate: [AuthGuardAdminCustomerService]},
  {path: "transaction", component: TransactionComponent, canActivate: [AuthGuardAdminCustomerService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
