export default class WritePostRequest {
  title: string;
  description: string;
  userId: number;

  constructor(userId: number, title: string, description: string) {
    this.userId = userId;
    this.title = title;
    this.description = description;
  }
}
