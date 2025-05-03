# step-1 고려한 사항

## 1. try-resources 구조를 사용한 Scanner 메모리 누수 방지

`try-with-resources` 구문은 `Scanner`와 같은 `Closeable` 객체를 자동으로 닫아 메모리 누수를 방지한다. close 형태를 실제로 관리하는 것을 까먹을 수도 있고, 어느 포인트에서 닫아야할지에 대한 부분을 고려해야한다. 그렇기 때문에 필요한 block 이 끝난다면, 이를 자동으로 닫아줄 수 있게 하여, 혹여나 발생할 수 있는 메모리 누수에 대한 문제를 해결하고자 하였다.

## 2. Wrapper Class Type vs Primitive Type

Wrapper Class 를 통해서 연산을 진행하면, `Auto-Boxing`과 `Auto-UnBoxing` 과 같은 작업이 지속되고, 이는 메모리나 GC 에서의 오버헤드를 발생 할 수 있다고 생각했다.

그렇기 때문에, 단순 연산 작업을 구성함에 있어서 Primitive Type을 채택하게 되었다.

## 3. 잘못된 입력 값으로 인한 예기치 못한 종료 방지

잘못된 입력이 들어 왔을 때, 예기치 못한 종료가 발생하여, 이를 try-catch로 잡아서 초기 입력이 바르게 들어올 수 있도록 구성했다.
