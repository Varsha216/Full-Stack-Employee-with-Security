import { EmployeeRegistrationService } from '../service/employee-registration.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/Models/Employee';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  id:number;
  employee: any;

  constructor(private service: EmployeeRegistrationService,
    private route:ActivatedRoute,
    private router: Router) { }

  ngOnInit(){
    this.id = this.route.snapshot.params['id'];
    this.service.getUserById(this.id).subscribe(data => {
      console.log(data)
      this.employee = data});
  }

  onSubmit(){
    this.service.updateUser(this.id, this.employee).subscribe(data => console.log(data));
    this.router.navigate(['actions']);
  }

}
