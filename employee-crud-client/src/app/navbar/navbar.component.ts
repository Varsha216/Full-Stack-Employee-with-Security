import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userName: any;
  isLoggedIn : boolean = false;
  isRegistering : boolean = false;
  isRegisteringEmp: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userName = localStorage.getItem("userName");
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isRegistering = this.authService.getIsRegistering();
  }


  doLogout(){
    console.log("Logged out....");
    this.authService.logout();
  }

  doRegisteration() {
    this.authService.setIsRegistering(true);
    this.router.navigate(['registerAdmin']);
  }

  registerEmp(){
    this.authService.setIsRegisteringEmp(true);
    this.router.navigate(['register']);
  }

}
