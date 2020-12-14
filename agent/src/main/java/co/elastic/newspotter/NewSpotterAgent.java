package co.elastic.newspotter;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import akka.stream.ActorMaterializer;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class NewSpotterAgent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("NewSpotterAgent.premain");

        new AgentBuilder.Default()
                .ignore(none())
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION)
                .type(isSubTypeOf(ActorMaterializer.class))
                .transform((builder, type, classLoader, module) ->
                        builder.visit(Advice
                                .to(ConstructionTemplate.class)
                                .on(isConstructor().and(takesArguments(0))))
                )
                .installOn(instrumentation);
    }
}
