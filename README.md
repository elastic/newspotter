# NewSpotter

This project creates a java agent used for logging when new instances of ActorMaterializer is
created, for spotting potential memory leaks.


## Building

```
mvn package
```

## Running with test class

The test class is just a simple class that allocates an ActorMaterializer and sleeps. It should print out the stacktrace for the allocation.
```
java -javaagent:agent/target/newspotter-agent-1.0-SNAPSHOT.jar -jar agent/target/newspotter-agent-1.0-SNAPSHOT.jar
```
Example output:
```
NewSpotterAgent.premain
New ActorMaterializer
java.lang.RuntimeException: Created: PhasedFusingActorMaterializer(akka://iot-system,ActorMaterializerSettings(4,16,,<function1>,StreamSubscriptionTimeoutSettings(CancelTermination,5000 milliseconds),false,1000,1000,false,true,IoSettings(16384)),akka.dispatch.Dispatchers@532a02d9,Actor[akka://iot-system/system/StreamSupervisor-0#882213536],false,akka.stream.impl.SeqActorNameImpl@7cbee484)
	at akka.stream.impl.ExtendedActorMaterializer.<init>(ActorMaterializerImpl.scala:26)
	at akka.stream.impl.PhasedFusingActorMaterializer.<init>(PhasedFusingActorMaterializer.scala:386)
	at akka.stream.ActorMaterializer$.apply(ActorMaterializer.scala:69)
	at akka.stream.ActorMaterializer$.apply(ActorMaterializer.scala:43)
	at akka.stream.ActorMaterializer.apply(ActorMaterializer.scala)
	at co.elastic.newspotter.TestMain.main(TestMain.java:17)
```