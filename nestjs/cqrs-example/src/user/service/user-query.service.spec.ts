import { Test, TestingModule } from '@nestjs/testing';
import { UserQueryService } from './user-query.service';

describe('User', () => {
  let provider: UserQueryService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [UserQueryService],
    }).compile();

    provider = module.get<UserQueryService>(UserQueryService);
  });

  it('should be defined', () => {
    expect(provider).toBeDefined();
  });
});
