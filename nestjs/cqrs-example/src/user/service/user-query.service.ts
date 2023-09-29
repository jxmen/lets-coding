import { Inject, Injectable } from '@nestjs/common';
import UserResponse from '../dto/UserResponse';
import UserRepository, { User } from '../user.repository';

export const USER_REPOSITORY_TOKEN = 'USER_REPOSITORY';

@Injectable()
export class UserQueryService {
  constructor(
    @Inject(USER_REPOSITORY_TOKEN)
    private readonly userRepository: UserRepository,
  ) {}

  async getUsers(): Promise<UserResponse[]> {
    const users: User[] = await this.userRepository.findAll();

    return users.map((user) => new UserResponse(user));
  }
}
