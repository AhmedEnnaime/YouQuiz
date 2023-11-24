export class MenuItem {
  text: String;
  url: String;
  notificationCount: Number;
  [key: string]: any;

  constructor(text: String, url: String, notificationCount: Number) {
    this.text = text;
    this.url = url;
    this.notificationCount = notificationCount;
  }
}
