import { Injectable } from '@nestjs/common';
import { InjectRedis, Redis } from '@nestjs-modules/ioredis';
import ChangeUserRequest from './dto/ChangeUserRequest';

@Injectable()
export class UserService {
  private readonly redis: Redis;

  constructor(@InjectRedis() redis: Redis) {
    this.redis = redis;
  }

  async changeUser(userId: number, request: ChangeUserRequest) {
    await this.redis.set(
      `user:${userId}`,
      JSON.stringify({
        id: userId,
        ...request,
      }),
    );

    await this.redis.sadd('users', `user:${userId}`);
  }

  async getUser(userId: number) {
    return await this.redis.get(`user:${userId}`);
  }

  async getUsers() {
    const users = await this.redis.smembers('users');

    return Promise.all(
      users.map(async (userStr) => {
        const splitElement = userStr.split(':')[1];
        const user = await this.getUser(Number(splitElement));

        return JSON.parse(user);
      }),
    );
  }
}
