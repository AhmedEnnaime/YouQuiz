import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'src/app/core/MenuItem';
import { faSearch, faBell } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  faSearch = faSearch;
  faBell = faBell;

  menuItems: MenuItem[] = [
    {
      text: 'Dashboard',
      url: '/',
      notificationCount: 0,
    },
    {
      text: 'Students',
      url: '/students',
      notificationCount: 4,
    },
    {
      text: 'Quizzes',
      url: '/quizzes',
      notificationCount: 0,
    },
    {
      text: 'Subjects',
      url: '/subjects',
      notificationCount: 0,
    },
    {
      text: 'Listing',
      url: '/listings',
      notificationCount: 0,
    },
  ];

  ngOnInit(): void {
    this.menuItems = this.menuItems.map((item: MenuItem) => {
      return {
        ...item,
        isActive: window.location.pathname === item.url,
      };
    });
  }
}
