import { ElementSchemaRegistry } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { Employee } from 'src/app/Models/Employee';
import { EmployeeRegistrationService } from '../service/employee-registration.service';

@Component({
  selector: 'app-actions',
  templateUrl: './actions.component.html',
  styleUrls: ['./actions.component.css']
})
export class ActionsComponent implements OnInit {

  employees: any;
  id: number ;

  constructor(private service:EmployeeRegistrationService,
    private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    let resp = this.service.getUsers();
    resp.subscribe((data) => this.employees = data);
  }

  deleteEmployee(id:number){
    this.service.deleteUser(id)
      .subscribe(data => {
          console.log(data);
          this.reloadData();
        })
  }

  updateEmployee(id:number){
    this.router.navigate(['update',id]);
  }

  findEmployeeById(){
    this.router.navigate(['employeeById', this.id]);
  }


}
