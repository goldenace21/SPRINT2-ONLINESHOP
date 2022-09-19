import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Product} from "../model/Product";
import {Cart} from "../model/Cart";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private API = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  getCart(username: string): Observable<Cart[]> {
    return this.httpClient.get<Cart[]>(this.API + `/cart?username=${username}`);
  }

  upQuantity(username: string, idProduct: number): Observable<void> {
    return this.httpClient.get<void>(this.API + `/cart/up?username=${username}&idProduct=${idProduct}`);
  }

  downQuantity(username: string, idProduct: number): Observable<void> {
    return this.httpClient.get<void>(this.API + `/cart/down?username=${username}&idProduct=${idProduct}`);
  }

  addToCard(id: number,username:string): Observable<any> {
    return this.httpClient.get<any>(this.API + `/cart/add?id=${id}&username=${username}`);
  }

  remove(id: number,username:string): Observable<any> {
    return this.httpClient.delete<any>(this.API + `/cart?id=${id}&username=${username}`);
  }

}
