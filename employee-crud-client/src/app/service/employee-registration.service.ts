import { Employee } from '../Models/Employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeRegistrationService {

  private baseUrl = 'http://localhost:8080/employee/';

  constructor(private http: HttpClient) { }

  public doRegistration(Employee: Employee){
    return this.http.post("http://localhost:8080/employee",Employee,
            {
                  headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem("token")}`)
            });
  }

  public getUsers(){
    return this.http.get("http://localhost:8080/employee",
            {
                  headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem("token")}`)
            });
  }

  public getUserById(id: number){
    return this.http.get("http://localhost:8080/employee/"+id,
          {
                headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem("token")}`)
          });
  }

  public deleteUser(id : number){
    return this.http.delete("http://localhost:8080/employee/"+id,
            {
                headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem("token")}`)
            });
  }

  public updateUser(id: number, value:any){
    return this.http.put(`${this.baseUrl}${id}`, value,
            {
                  headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem("token")}`)
            });
  }
}
