import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    roles: new FormControl(['customer'])
  });

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  submit() {
    console.log(this.registerForm.value)
    this.authService.signUp(this.registerForm.value).subscribe(
      value => {this.router.navigateByUrl("/login")},
      error => {},
      () => {}
    )
  }
}
