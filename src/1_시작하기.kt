// 1_시작하기

// 1) .kt 확장자를 가집니다.
// 2) public class를 위한 별도의 파일 생성이 필요하지 않습니다.

/*
public class Sample {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
*/

// 3) 전역 함수를 허용합니다.
// 4) 코틀린 언어 특징
// - 간결함: 자바 언어에서 사용하던 많은 보일러플레이트를 제거합니다.
// - 안전함: 널 안정성을 위한 기능을 제공합니다.(Nullable)
// - 상호 운용: 자바의 코드를 코틀린에서 이용할 수 있고, 코틀린에서 작성된 코드를 자바에서 이용할 수 있다.
// 5) REPL(Read-Eval-Print-Loop) 제공합니다.
fun main() {
    println("Hello, Kotlin")
}

/*              javac
// Hello.java    ->     Hello.class
public class Hello {
  public Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello, Java
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

*/

/*       kotlinc
// Hello.kt -> HelloKt.class
public final class HelloKt {
  public static final void main();
    Code:
       0: ldc           #11                 // String Hello, Kotlin
       2: astore_0
       3: iconst_0
       4: istore_1
       5: getstatic     #17                 // Field java/lang/System.out:Ljava/io/PrintStream;
       8: aload_0
       9: invokevirtual #23                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      12: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #9                  // Method main:()V
       3: return
}

public final class HelloKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkParameterIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin
       8: astore_1
       9: iconst_0
      10: istore_2
      11: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      14: aload_1
      15: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      18: return
}
Caused by: java.lang.ClassNotFoundException: kotlin.jvm.internal.Intrinsics
❯ kotlin HelloKt
// or
❯ kotlinc Hello.kt -include-runtime -d Hello.jar
❯ java -jar Hello.jar
Hello, Kotlin
*/

