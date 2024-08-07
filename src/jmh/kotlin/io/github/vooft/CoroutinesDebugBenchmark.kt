package io.github.vooft

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
@Fork(value = 2)
open class CoroutinesDebugBenchmark {

    @Param("on", "off")
    lateinit var kotlinCoroutinesDebug: String

    @Setup
    fun setup() {
        System.setProperty(DEBUG_PROPERTY_NAME, kotlinCoroutinesDebug)
    }

    @Benchmark
    fun test(): Unit? {
        return runBlocking { runCatching { aaaa() }.getOrNull() }
    }

    suspend fun aaaa() {
        delay(1)
        withContext(Job() + CoroutineName("bbbb")) {
            bbbb()
        }
    }

    suspend fun bbbb() {
        delay(1)
        withContext(Job() + CoroutineName("cccc")) {
            cccc()
        }
    }

    suspend fun cccc() {
        delay(1)
        withContext(Job() + CoroutineName("dddd")) {
            dddd()
        }
    }

    suspend fun dddd() {
        delay(1)
        withContext(Job() + CoroutineName("eeee")) {
            eeee()
        }
    }

    suspend fun eeee() {
        delay(1)
        error("Boo")
    }
}
