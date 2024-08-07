# coroutines-debug-benchmark
A simple benchmark to compare the performance of coroutines when [DEBUG_PROPERTY_NAME](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-d-e-b-u-g_-p-r-o-p-e-r-t-y_-n-a-m-e.html) is enabled or not.

# How to run
`make benchmark`

# Methodology
This is a simple benchmark with 5 nested suspend functions that delay for 1ms each and then either call next suspend function, or throw an exception.

Each nested call is wrapped with `withContext` that changes a coroutine name to force a new coroutine context to be created and stacktrace to be captured for debug mode.

# Results

Results demonstrate that enabling `DEBUG_PROPERTY_NAME` does not have a significant performance impact on coroutines.

GitHub Actions `ubuntu-latest` with Temurin JDK 21:
```
Benchmark                      (kotlinCoroutinesDebug)   Mode  Cnt    Score   Error  Units
CoroutinesDebugBenchmark.test                       on  thrpt   10  185.938 ± 0.121  ops/s
CoroutinesDebugBenchmark.test                      off  thrpt   10  187.970 ± 0.071  ops/s
```
