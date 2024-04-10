import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AdminProfile } from '../Models/admin-profile';
import { AuthRequest } from '../Models/auth-request';
import { AuthResponse } from '../Models/auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public authResponse: any;
  public isRegistering: boolean = false;
  public isRegisteringEmp: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  welcome(){
    return this.http.get("http://localhost:9191/auth/welcome", { responseType: 'text'});
  }

  registerAdmin(adminProfile : AdminProfile){
    return this.http.post("http://localhost:9191/auth/register", adminProfile);
  }

  authenticate(authRequest : AuthRequest){
    return this.http.post("http://localhost:9191/auth/authenticate", authRequest, 
                          { responseType : 'text',
                            //observe : 'response'
                          });
  }

  // validate(securityToken : string){
    validate(){
    return this.http.get<AuthResponse>("http://localhost:9191/auth/validate",
                            {
                              headers: new HttpHeaders().set('Authorization', `Bearer ${this.getToken()}`)
                            });
  }


  setToken(jwt: string){
    localStorage.setItem("token", jwt);
    return true;
  }

  getToken(){
    return localStorage.getItem("token");
  }

  isLoggedIn():boolean {
    let token = localStorage.getItem("token");
    if(token==undefined || token==null || token==='')
      return false;
    else 
      return true;
  }

  logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("userName");
    this.authResponse = null;
    this.router.navigate(['login']);
    return true;
  }

  setAuthResponse(authResponse: AuthResponse){
    localStorage.setItem("userId", authResponse.id+"");
    localStorage.setItem("userName", authResponse.name);
    this.authResponse = authResponse;
  }

  getAuthResponse(): AuthResponse{
    return this.authResponse;
  }

  setIsRegistering(status: boolean){
    this.isRegistering = status;
  }
  getIsRegistering(){
    return this.isRegistering;
  }

  setIsRegisteringEmp(status: boolean){
    this.isRegisteringEmp = status;
  }
  getIsRegisteringEmp(){
    return this.isRegisteringEmp;
  }
}
