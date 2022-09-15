import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/Product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private API = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  getList(name: string, limit: number, cate: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.API + `/list?name=${name}&limit=${limit}&cate=${cate}`);
  }

  detail(id: string): Observable<Product> {
    return this.httpClient.get<Product>(this.API + `/list/detail?id=${id}`);
  }
}
