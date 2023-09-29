import { Test, TestingModule } from '@nestjs/testing';
import { UserController } from './user.controller';
import CreateUserRequest from '../dto/CreateUserRequest';

describe('UserController', () => {
  let controller: UserController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [UserController],
    }).compile();

    controller = module.get<UserController>(UserController);
  });

  it('should be defined', () => {
    const req: CreateUserRequest = new CreateUserRequest('jxmen');
    const actual = controller.createUser(req);

    expect(actual).toBeDefined();
    expect(actual).not.toBeNull();
  });
});
