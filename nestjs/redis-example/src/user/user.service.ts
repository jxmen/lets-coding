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
  }

  async getUser(userId: number) {
    return await this.redis.get(`user:${userId}`);
  }
}
