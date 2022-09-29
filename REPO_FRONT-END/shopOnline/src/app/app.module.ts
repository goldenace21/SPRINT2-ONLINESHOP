import { BrowserModule } from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { HomePageComponent } from './common/home-page/home-page.component';
import { DetailComponent } from './common/detail/detail.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./common/login/login.component";
import {ListComponent} from "./common/list/list.component";
import { RegisterComponent } from './common/register/register.component';
import { CartComponent } from './common/cart/cart.component';
import { Error401Component } from './common/error/error401/error401.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import { ProfileComponent } from './common/profile/profile.component';
import { TransactionComponent } from './common/transaction/transaction.component';
import { CreateComponent } from './common/create/create.component';
import {environment} from "../environments/environment";
import {AngularFireModule} from "@angular/fire";
import {AngularFireStorage} from "@angular/fire/storage";
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomePageComponent,
    LoginComponent,
    DetailComponent,
    ListComponent,
    RegisterComponent,
    CartComponent,
    Error401Component,
    ProfileComponent,
    TransactionComponent,
    CreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    ToastrModule.forRoot({
      timeOut: 2000,
      closeButton: true,
      progressBar: true,
      positionClass: 'toast-top-right',
    }),
  ],
  providers: [AngularFireStorage]
  ,
  bootstrap: [AppComponent]
})
export class AppModule { }
