import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  private API = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  receipt(username:string): Observable<any> {
    return this.httpClient.get<any>(this.API + `/receipt/add?username=${username}`);
  }
}
