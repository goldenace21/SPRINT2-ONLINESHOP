import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReloadService {

  // private subject = new Subject<any>()
  //
  // constructor() { }
  //
  // sendClickEvent(){
  //   this.subject.next()
  // }
  //
  // getClickEvent(): Observable<any>{
  //   return this.subject.asObservable();
  // }

  messageSource = new BehaviorSubject<string>("none");
  currentMessage = this.messageSource.asObservable();
  // có thể subcribe theo dõi thay đổi value của biến này thay cho messageSource
  constructor() { }
  // method này để change source message
  changeMessage(message) {
    this.messageSource.next(message);
  }
}
