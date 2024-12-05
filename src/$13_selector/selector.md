## 13. Selector
### 강의 내용
```
- Selector 개요
- 
- 
- 
```
### 1. Selector 개요
- **Reactor 디자인 패턴**
    > **클라이언트의 요청을 앞단의 queue에 저장 <br> -> queue를 모니터링 한느 thread에 이벤트 전달** <br> **-> 모니터링 하는 thread가 queue에 저장된 요청 방향 분석 <br> -> 적절한 프로세스 로직으로 보내주어 해당 요청 처리**

    - **NIO Non-Blocking 서버 구현의 밑바탕(Readctor 패턴)**
    - **즉, Selector는 바로 Reactor 역할**
- Selector 역할
    >SelectableChannel을 자신에게 등록 <br>-> 등록된 SelectableChannel의 이벤트 요청 구분 <br>-> 적절한 서비스 제공자에게 보내 처리
    - **즉, Multiplex IO를 가능하게 해 준다.**
  

### 2. 기존의 네트워크 프로그래밍 모델
- Blocking IO
  - r/w 작업을 할 때, 데이터를 이용할 수 있을 때까지 해당 IO 작업을 수행하려던 thread가 아무것도 하지 않고 대기하는 것
- Blocking IO 예시
  - Socket 에서 BufferedRead 객체를 얻어 이 Stream 으로부터 데이터를 읽어 오기 위해 readLine() 호출
  - readLine() 호출했을 때,해당 Stream 으로부터 \n 을 읽을 때까지 이 Thread는 Blocking


- **Multi Thread 모델의 문제점**
  - 많은 Thread 생성에 따른 Thread context switch 부하
    - 보통 경량 서버의 경우, 1,000개의 thread가 생성되면 thread context 부하로 급격한 성능 저하 발생
    - thread 자체가 CPU와 고유 스택을 갖는데 따른 컴퓨터 리소스 부하
  - 클라이언트의 빈번한 접속과 종료에 따라 많은 가비지가 생성
    - 클라이언트와 데이터를 주고받는 과정에서 JVM 힙 영역으로부터 데이터의 복사가 빈번하게 발생하므로 많은 가비지 밯생
    - Thread의 잦은 생성과 소멸에 따른 가비지도 발생
  - 클라이언트가 접속할 때마다 매번 thread를 새로 생성하는 부담
  - 서버의 메모리가 부족해서 OutOfMemoryException 발생할 수도 있음

    

### 3. Non-Blocking 모델
- Non Blocking 모드를 사용해서 Multi-Thread 서버와 같은 동작
  - **채널을 Selector에 등록하면 채널과 Selector 사이의 관계를 캡슐화한 SelectionKey가 Selector에 저장**
  - 어떤 채널이 자신이 등록한 mode 에 대해 동작할 준비가 되면 SelectionKey는 그 준비상태를 내부적으로 저장
  - Selector가 select() 메소드를 호출해서 자신에게 등록된 모든 SelectionKey 들을 검사하는데, 바로 동작할 준비가 되어 있는지 아닌지를 검사
  - >**Multiplex 모델의 서버를 만들기 위해 핵심적인 역할을 하는 것은 <br> Selector, SelectableChannel, SelectionKey 이다.**
### 4. SelectableChannel
### 5. SelectionKey
### 6. Selector API

### 7. Non-Blocking 서버 만들기
