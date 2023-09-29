export interface User {
  id: number;
  name: string;
}

export default interface UserRepository {
  findAll(): Promise<User[]>;
}
