import { PrismaModule } from 'nestjs-prisma';

export const testPrismaModule = PrismaModule.forRootAsync({
  isGlobal: true,
  useFactory: async () => {
    return {
      prismaOptions: {
        datasources: {
          db: {
            // TODO: 이게 맞나???
            url: 'file:./test.db',
          },
        },
      },
    };
  },
});
