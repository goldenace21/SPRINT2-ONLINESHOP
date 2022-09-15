import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {SignUpForm} from "../model/SignUpForm";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LoginForm} from "../model/LoginForm";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_SIGNUP = environment.apiUrl + "/authenticate"

  constructor(private http: HttpClient) {}

  signUp (signUp: SignUpForm): Observable<any> {
    return this.http.post<any>(this.API_SIGNUP + '/signup', signUp);
  }

  login (logIn: LoginForm): Observable<any> {
    return this.http.post<any>(this.API_SIGNUP + '/signin', logIn);
  }
}
