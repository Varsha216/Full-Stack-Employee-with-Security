import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminProfile } from '../Models/admin-profile';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css']
})
export class RegisterAdminComponent implements OnInit {

  adminProfile: AdminProfile = new AdminProfile();
  message: string="";

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  registerAdmin(){
    this.authService.registerAdmin(this.adminProfile)
                              .subscribe({
                                next: (data) =>{
                                  console.log(data);
                                  this.router.navigate(['login']);
                                },
                                error: (error) => this.message = error,
                                complete: () =>{
                                  this.authService.setIsRegistering(false);
                                  this.router.navigate(['login']);
                                }
                              })
  }

}
