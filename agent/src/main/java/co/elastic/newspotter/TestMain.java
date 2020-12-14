package co.elastic.newspotter;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import scala.None$;
import scala.Option;


public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("New ActorMaterializer");
        Option<ActorMaterializerSettings> empty = None$.empty();
        Option<String> empty2 = None$.empty();
        ActorSystem actorSystem = ActorSystem.create("iot-system");

        ActorMaterializer actorMaterializer = ActorMaterializer.apply(empty, empty2, actorSystem);
        Thread.sleep(10000);
    }
}

