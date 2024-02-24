import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from '../model/user';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UserService } from '../Services/user.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-user-manager',
  templateUrl: './user-manager.component.html',
  styleUrls: ['./user-manager.component.css']
})
export class UserManagerComponent implements OnInit {

  displayedColumns: string[] = ['id', 'username', 'email', 'roles','edit'];
  dataSource!: MatTableDataSource<User>;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private userService: UserService, private router:Router) {

    //this.dataSource = new MatTableDataSource(users);
  }
  ngOnInit(): void {

    console.log("user manager activated....");
    this.userService.getAllUsers().subscribe({
      next: data => {

        console.log("fetched data-->" + data);
        this.dataSource = new MatTableDataSource(data);
      },
      error: error => {

        console.log("facing issue while fetching the data");
      }
    })
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  navigateToView(userId:string){
    console.log("recived user id "+userId);
  this.router.navigate(['updateuserrole',userId]);

  }

}
