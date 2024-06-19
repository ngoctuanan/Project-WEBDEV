import { Component } from '@angular/core';
import {  Router } from '@angular/router';
import { UserStoargeService } from './basic/components/services/stogare/user-stoarge.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'SeviceBookingSystemWeb';

  

  isClientLoggedIn: boolean =  UserStoargeService.isClientLoggedIn();
  isCompanyLoggedIn: boolean = UserStoargeService.isCompanyLoggedIn();

  constructor(private router: Router) {}

  ngOnInit(){
    this.router.events.subscribe(event => {
        this.isClientLoggedIn =  UserStoargeService.isClientLoggedIn();
        this.isCompanyLoggedIn = UserStoargeService.isCompanyLoggedIn();  
    })
  }
  logout(){
    UserStoargeService.signOut();
    this.router.navigateByUrl('login');
  } 
 }

