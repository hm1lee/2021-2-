



## 디지털 포렌식 2주차 - **저장 장치와 파일 시스템**



### 01. 문자의 표현과 파일 카빙



**[ 문자, 숫자의 표현 ]**



바이너리, 비트, 바이트로 구성 (비트 8개 = 1 바이트 )



(1) ASCII 



![img](https://media.vlpt.us/images/exploit017/post/9eb5ba8c-326f-4ede-9706-7c0d260c6301/image.png)



(2) Hexa decimal (16진수) 표현



```javascript
0x4D = 01001101 = M
61h = 01100001 = a  -> 61h = 0x61
```



**[ 파일 카빙 ]**

* 파일의 위치를 확인하고 복원하기 위해서 사용하는 방법
* 파일 헤더(header) / 푸터(footer) 정보 이용 : 파일의 시작점과 끝점 정보 활용하기 (File Signiture 활용)
* 바이너리와 16진수를 해석할 수 있는 능력 필요





**[ 파일 확장자와 파일 시그니처 ]**



\- 운영체제는 **파일 확장자(docx, pptx, hwp)**를 이용하여 파일을 식별

\- 파일 확장자는 사용자가 변경이 가능하다 !

\- 파일 확장자를 이용하여 오히려 데이터를 숨길 수 있음

\- 포렌식에서는 **파일의 헤더(header)**로 파일을 식별함

\- Gary Kessler file signature 

(https://attackersmindset.com/2018/05/18/forensics-1-file-signature-analysis/)







### 02. 다양한 저장 장치

**[ 저장장치와 메모리 ]**

**- 전자기 (하드디스크)**



![스크린샷_2021-09-15_오전_2.03.50.png](https://blogfiles.pstatic.net/MjAyMTA5MTVfMjEw/MDAxNjMxNjM5MDM1MDY1.HKylNyJr-kkzHhlW176GG3FBc33Hs4AyVWjMel_qUe8g.Ec2GEcKIezPOeLOewXRThyP5b-NxC2Tnp9LinyaGCTYg.PNG.nm1lee/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2021-09-15_%EC%98%A4%EC%A0%84_2.03.50.png?type=w1)

https://blog.daum.net/dasomcap/911



**- 반도체 트랜지스터 (플래시)**

\- USB, 메모리카드 : 트랜지스터로 구성되나 전기가 차단되어도 데이터가 지워지지 않음

\- 플래시 기반의 하드디스크 (SSD)



![img](http://gdimg.gmarket.co.kr/1715694760/still/600?ver=1575532541)

(메모리카드)

![img](https://www.powerplanetonline.com/cdnassets/disco_duro_ssd_1tb_sandisk_plus_sata3_02_ad_l.jpg)

(SSD)



**- 빛의 반사 (CD, DVD)**

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Interference-colors.jpg/1200px-Interference-colors.jpg)





**[ 하드 디스크와 디지털 포렌식 ]**

**하드 디스크는 섹터(512 바이트) 단위로 데이터를 저장한다!**

\- 파일의 크기가 1024 바이트라고 한다면 두 개의 섹터가 필요함

\- 만약 범인이 2개의 섹터에 대한 파일을 삭제했다고 가정해보면, 파일 저장에 사용되었던 2개의 섹터는 그대로 존재하게 된다.

\- 780 바이트 새로운 파일 역시, 2개의 두 개의 섹터가 필요

\- 우연히 새로운 파일을 이전 새로운 파일이 저장되어 있던 1024 바이트 섹터에 저장한다면? 

\- 나머지 244는 덮어써지지 않는데 -> 244 바이트의 슬랙 공간이 생긴다고 한다.

\- 슬랙 공간을 복구하여 증거를 수집





**[ 하드 디스크 파기 방법 ]**



1. 소프트웨어적 파기 (Wiping)

**- zero filling** : 모든 데이터를 0으로 덮어 쓰기

**- US DoD** : 임의의 데이터를 덮어쓰고, 그 데이터의 보수로 또 덮어쓰고, 랜덤 값으로 한번 더 덮어쓰기 (3 pass, 7 pass)

ex) 1010 (임의의 데이터) -> 0101 (보수 값으로 덮어쓰기) -> 랜덤 값으로 한번 더 덮어쓰기 -> ...

**- Peter Gutmann** : 랜덤 데이터 4회, 패턴 데이터 27회, 랜덤 데이터 4회 (총 35 pass)

\- BCWipe, MooO, CCleaner 등의 도구를 사용 (실습시에 MooO를 사용할 예정...)



2. 포맷(High Level)도 소프트웨어 파기에 해당될까? 

\- No, 포렌식으로 내용 복구 가능하다.

\- 하드웨어적 파기 방법





[ SSD와 디지털 포렌식 ]

1. SSD의 특징



\- 빠른 속도, 충격 내구성, 저전력

\- NOR(휴대폰 메모리) 방식과 NAND(SD 카드 USB 메모리, SSD) 방식

\- 블록(512KB)과 페이지(4KB) 단위로 쓰기

\- 각 블록의 쓰기 회수가 정해져있음 (약 1,000- 10,000회 )





![img](https://lh3.googleusercontent.com/proxy/uWiT_p1f2aoJfpdL9mmTsjy9ZM9nJL20FPGLxY_-I3vnonCkffAxDLYw2_fj-f5rjen_6PdRVJw4VQGSPweIPzGNfY7-g075VOeagWqe2w1gKNUp)



\- 같은 블록에 계속 쓰기를 해야 함. 아니면 특정 영역의 수명이 단축된다.

\- 각 셀을 고루 사용하도록 wear leveling 기법 사용

\- 가비지 컬렉션 동안 증거 데이터가 자동으로 파과되어 증거의 무결성 확인이 어려워짐

\- 가비지 컬렌션이 증거 수집 동안 또는 이후 이루어지면 해쉬값의 불일치 발생





2. Wear leveling

\- wear leveling 때문에 원하는 영역의 삭제가 제대로 이루어지지 않을 가능성이 높음. 기존 삭제 프로그램 무용지물

\- 제조사의 삭제 프로그램 또는 TRIM 기능 사용

\- wear leveing 때문에 복구도 쉽지 않음

\- 일반적인 파일 시스템 구조가 아님





[ 다양한 컴퓨팅 환경에서의 포렌식 ]

\- 독립형

\- 네트워크 (클라이언트-서버)

\- 메인프레임

\- 클라우드

\- IaaS/PaaS/SaaS 

- 기술적으로 매우 복잡한 가상환경을 제공함으로써 포렌식 관점에서 커다란 도전

* 법적으로 관활권 문제 이야기

﻿