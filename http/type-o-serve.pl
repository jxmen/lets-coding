#!/usr/bin/perl

use Socket;
use Carp;
use FileHandle;


# (1) 명령줄에서 덮어쓰지 않는 한 8080 포트를 기본으로 사용
$port = (@ARGV ? $ARGV[0] : 8080);

# (2) 로컬 TCP 소켓을 생성하고 커넥션을 기다리도록(listen) 설정
$proto = getprotobyname('tcp');
socket(S, PF_INET, SOCK_STREAM, $proto) || die;
setsockopt(S, SOL_SOCKET, SO_REUSEADDR, pack("l", 1)) || die;
bind(S, sockaddr_in($port, INADDR_ANY)) || die;
listen(S, SOMAXCONN) || die;

# (3) 시작 메시지 출력
printf("    <<<Type-O-Serve Accepting on Port %d>>>\n\n", $port);

while(1) {

    # (4) 커넥션 C를 기다림
    $cport_caddr = accept(C, S);
    ($cport,$caddr) = sockaddr_in($cport_caddr);
    C->autoflush(1);

    # (5) 누구로부터의 커넥션인지 출력한다.
    $cname = gethostbyaddr($caddr,AF_INET);;
    printf("    <<<요청 정보: %s>>>\n", $cname);

    # (6) 빈 줄이 나올때까지 요청 메시지를 읽어서 화면에 출력한다.
    while($line = <C>) {
        print $line;
        if($line =~ /^\r/) { last; }
    }

    # (7) 응답 메시지를 위한 프롬프트를 만들고, 응답줄을 입력 받는다.
    #   "." 하만으로 되어 있는 줄이 입력되기 전까지, 입력된 줄을 클라이언트에게 보낸다. 

    printf("    <<<보낼 응답 텍스트와 마지막에 '.'을 입력하세요. >>>\n");

    while($line = <STDIN>) 
    {
        $line =~ s/\r//;
        $line =~ s/\n//;
        if ($line =~ /^\./) { last; }
        print C $line . "\r\n";
    }
    close(C);
}

