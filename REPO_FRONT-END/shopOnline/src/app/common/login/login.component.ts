import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {Toast, ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private router: Router,
              private authService: AuthService,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.createLoginForm()
  }

  createLoginForm() {
    this.loginForm = new FormGroup({
      username: new FormControl(""),
      password: new FormControl("")
    });
  }

  signIn() {
    this.authService.login(this.loginForm.value).subscribe(
      value => {
        console.log(value)
        sessionStorage.setItem('username', this.loginForm.value.username);
        const tokenStr = 'Bearer ' + value.token;
        sessionStorage.setItem('token', tokenStr);
        sessionStorage.setItem('roles', value.roles[0].authority);
        this.router.navigateByUrl("/home");
        this.toast.success("Logged in successfully");
      },
      error => {
        console.log("error")},
      () => {}
    )
  }
}
