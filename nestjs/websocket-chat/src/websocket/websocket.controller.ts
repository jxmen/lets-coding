import { Controller, Get } from '@nestjs/common';

@Controller('websocket')
export class WebsocketController {
  @Get()
  getHello(): string {
    return 'WebSocket example';
  }
}
