package com.lge.ex22;

import java.util.Optional;

public class Sample {
    public static void main(String[] args) {
        // Kotlin - Nullable 문법
        // Java   - Optional 라이브러리
        Optional<String> s = Optional.of("hello");
        if (s.isPresent()) {
            System.out.println(s.get());
        }

    }
}
