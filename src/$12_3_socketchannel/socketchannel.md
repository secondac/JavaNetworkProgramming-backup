
## 12-3. 소켓 채널

### 강의 내용
```agsl

```

#### 소켓 채널
- **non-blocking** 모드 지원
    > **Blocking**: 기존 소켓이나 IO를 이용한 N/W 프로그래밍의 최대 단점이었음 <br>
  > 클라이언트의 연결 요청을 수신하는 서버 소켓의 accept() 호출 시 <br>
  > 클라이언트와 연결된 소켓에서 데이터를 읽을 때
  > - non - blocking 효과를 내기 위해 multi-thread 사용했으나, thread가 많아질수록 성능이 저하됨
  > - 그래서 NIO는 처리 작업 수가 많고, 하나의 작업이 오래 걸리지 않는 경우에 사용하는 것이 좋다
- 그래서? **채널** 도입
    > **SocketChannel의 Interface** <br>
  > - ByteChannel, ScatteringByteChannel, GatheringByteChannel 
  > - 소켓 채널은 r / w 모두 가능
  > - 효율적인 r / w 를 위한 Scatter / Gather이용
  > - **서버 소켓은 스스로 데이터 전달 불가능** => 서버-클라이언트 연결만 수행
- Non - blocking 모드
    > **SelectableChannel** 에서 **configureBlocking, isBlocking, blockingLock** 사용 <br>
  > - **blockingLock()**
  >   - Object 타입
  >   - 소켓 채널의 blocking 모드 변경을 막기 위해 사용
  >   - return 된 객체는 소켓 채널의 blocking 모드를 변경하려고 할 때 사용
  >   - lock은 blockingLock() 통해 return 된 객체를 synchronized 키워드의 대상으로 사용해서 얻는다
  >   - 코드 임계영역이 실행되는 동안 다른 thread에 의해 소켓 채널의 blocking 모드가 변경되는 것을 막거나 다른 thread에 영향을 주지 않고 소켓 채널의 blocking 모드를 잠시 변경할 수 있게 한다
  >   - *일반적으로 서버 측에서 사용*
  >