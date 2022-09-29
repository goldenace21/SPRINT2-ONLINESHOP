import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-error401',
  templateUrl: './error401.component.html',
  styleUrls: ['./error401.component.css']
})
export class Error401Component implements OnInit {

  constructor( private title: Title) {
    this.title.setTitle("Apple - 401")
  }

  ngOnInit() {
  }

}
