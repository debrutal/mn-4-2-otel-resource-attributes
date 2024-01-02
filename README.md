# Project for a specific issue at micronaut.

Open Telemetry was upgraded to the latest version, which was not reflected in the classes being used, as the Class `ResourceAttributes` was moved from `io.opentelemetry.semconv.resources.attributes` to`io.opentelemetry.semconv`

# Retest:

start the application... after a few seconds you will encounter the following error message:


> i used intellij run on Application.java. 
> `mvn mn:run` is not having a different issue (though the actual problem is class not found)

```java
Exception in thread "BatchSpanProcessor_WorkerThread-1" java.lang.NoClassDefFoundError: io/opentelemetry/semconv/resource/attributes/ResourceAttributes
	at com.google.cloud.opentelemetry.trace.TraceVersions.readSdkVersion(TraceVersions.java:34)
	at com.google.cloud.opentelemetry.trace.TraceVersions.<clinit>(TraceVersions.java:29)
	at com.google.cloud.opentelemetry.trace.InternalTraceExporter.<clinit>(InternalTraceExporter.java:55)
	at com.google.cloud.opentelemetry.trace.TraceExporter.lambda$new$0(TraceExporter.java:41)
	at com.google.common.base.Suppliers$NonSerializableMemoizingSupplier.get(Suppliers.java:181)
	at com.google.cloud.opentelemetry.trace.TraceExporter.export(TraceExporter.java:93)
	at io.opentelemetry.sdk.trace.export.BatchSpanProcessor$Worker.exportCurrentBatch(BatchSpanProcessor.java:327)
	at io.opentelemetry.sdk.trace.export.BatchSpanProcessor$Worker.run(BatchSpanProcessor.java:245)
	at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.lang.ClassNotFoundException: io.opentelemetry.semconv.resource.attributes.ResourceAttributes
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:525)
	... 9 more
```
