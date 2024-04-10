import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/Models/Employee';
import { EmployeeRegistrationService } from '../service/employee-registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  employee: Employee=new Employee();

  constructor(private service:EmployeeRegistrationService,
    private router:Router) { }

  ngOnInit(): void {
  }

  public onSubmit(){
    let resp=this.service.doRegistration(this.employee);
    resp.subscribe((data)=>console.log(data));
    this.router.navigate(['actions']);
  }

}
