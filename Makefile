build:
	./gradlew clean jmhJar

benchmark: build
	java -XX:-BackgroundCompilation -jar build/libs/coroutines-debug-benchmark-1.0-SNAPSHOT-jmh.jar io.github.vooft.CoroutinesDebugBenchmark
