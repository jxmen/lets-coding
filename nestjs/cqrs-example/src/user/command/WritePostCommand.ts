import WritePostRequest from '../dto/WritePostRequest';

export default class WritePostCommand {
  userId: number;
  title: string;
  description: string;

  constructor(userId: number, writePostRequest: WritePostRequest) {
    this.userId = userId;
    this.title = writePostRequest.title;
    this.description = writePostRequest.description;
  }
}
