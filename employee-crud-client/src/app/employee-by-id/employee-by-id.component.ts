import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeRegistrationService } from '../service/employee-registration.service';

@Component({
  selector: 'app-employee-by-id',
  templateUrl: './employee-by-id.component.html',
  styleUrls: ['./employee-by-id.component.css']
})
export class EmployeeByIdComponent implements OnInit {
  employee: any;
  id: number;

  constructor(private service:EmployeeRegistrationService,
    private router:Router,
    private route:ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.reloadData();
  }

  reloadData(){
    let resp = this.service.getUserById(this.id);
    resp.subscribe(data => {console.log(data);this.employee = data});
  }

  deleteEmployee(id:number){
    this.service.deleteUser(id)
      .subscribe(data => {
          console.log(data);
          this.employeeList();
        })
  }

  employeeList(){
    this.router.navigate(['actions']);
  }

  updateEmployee(id:number){
    this.router.navigate(['update',id]);
  }

}
