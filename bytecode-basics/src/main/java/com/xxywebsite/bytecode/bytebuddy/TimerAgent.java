package com.xxywebsite.bytecode.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class TimerAgent {
    public static void premain(String arguments,
                               Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameEndsWith("Timed"))
                .transform((builder, type, classLoader, module, protectionDomain) ->
                        builder.method(ElementMatchers.any())
                                .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(instrumentation);
    }
}