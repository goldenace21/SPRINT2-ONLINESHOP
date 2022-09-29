import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/Product";
import {Category} from "../model/Category";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private API = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  getList(name: string, limit: number, cate: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.API + `/product?name=${name}&limit=${limit}&cate=${cate}`);
  }

  detail(id: string): Observable<Product> {
    return this.httpClient.get<Product>(this.API + `/product/detail?id=${id}`);
  }

  delete(id: string): Observable<Product> {
    return this.httpClient.get<Product>(this.API + `/product/delete?id=${id}`);
  }

  getListCategory(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.API + '/product/category')
  }

  save(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(this.API + '/product', product)
  }
}
