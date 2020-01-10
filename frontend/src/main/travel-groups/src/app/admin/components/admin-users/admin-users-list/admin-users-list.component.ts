import {Component, OnInit} from '@angular/core';
import {User} from '../../../../models';
import {AdminUserFacade} from '../../../facades/admin-user.facade';
import {Router} from '@angular/router';
import {FilterUtils} from 'primeng/utils';
import {ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-admin-users-list',
  templateUrl: './admin-users-list.component.html',
  styleUrls: ['./admin-users-list.component.css']
})
export class AdminUsersListComponent implements OnInit {

  users$ = this.userFacade.users$;

  users: User[] = [];

  cols: any[];

  constructor(public userFacade: AdminUserFacade, private router: Router, private confirmationService: ConfirmationService) {
  }


  ngOnInit() {
    this.userFacade.loadUsers().subscribe(data => {
      this.users = this.users$.value;
      console.log(this.users);
    });

    this.cols = [
      {field: 'id', header: 'ID'},
      {field: 'name', header: 'name'},
      {field: 'username', header: 'username'},
      {field: 'email', header: 'email'},
      {field: 'actions', header: 'actions'}
    ];

    FilterUtils['custom'] = (value, filter): boolean => {
      if (filter === undefined || filter === null || filter.trim() === '') {
        return true;
      }

      if (value === undefined || value === null) {
        return false;
      }

      return parseInt(filter) > value;
    };
  }


  onDelete(id: number) {
    console.log(id)
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this user?',
      accept: () => {
        this.userFacade.deleteUser(id).subscribe(
          () => this.users = this.users$.value
        );
      }
    });
  }

  onEdit(id: number) {
    this.router.navigateByUrl('/admin/edit-user/' + id);
  }
}
