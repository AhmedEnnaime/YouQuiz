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
      text: 'Inbox',
      url: '/inbox',
      notificationCount: 4,
    },
    {
      text: 'Calendar',
      url: '/calendar',
      notificationCount: 0,
    },
    {
      text: 'Insights',
      url: '/insights',
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
