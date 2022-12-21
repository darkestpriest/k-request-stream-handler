package request.handler

import com.fasterxml.jackson.core.JsonEncoding
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import request.handler.Constants.fixedKey
import request.handler.Constants.fixedValue
import request.handler.Constants.outStreamFixedSize
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import kotlin.test.assertFails

class HandlerStreamTest {

    private lateinit var sut: HandlerStream<*,*>

    @BeforeEach
    fun setup() {
        sut = StubbedHandlerStream()
    }

    @Test
    fun `when invoke sut with streams then retrieve a json response`() {
        //Given
        val expected = """
            {"$fixedKey":"$fixedValue"}
        """.trimIndent()

        //Then
        assertEquals(expected, prepareTestCase().toByteArray().decodeToString())
    }

    @Test
    fun `exceptions are propagated when request handler fails on process input`() {
        //Given
        val input = "any-unformatted"

        //Then
        assertFails {
            prepareTestCase(input)
        }
    }

    private fun prepareTestCase(input: String = """"$fixedValue""""): ByteArrayOutputStream {
        return ByteArrayOutputStream(outStreamFixedSize).also {
            input.byteInputStream().apply {
                sut.handleRequest(this, it, null)
                close()
            }
        }
    }
}


class StubbedHandlerStream: HandlerStream<String, String>() {

    override fun doHandle(input: String): String {
        return """
            $input
        """.trimIndent()
    }

    override fun parse(input: InputStream): String {
        return ObjectMapper().readValue(input, String::class.java)
    }

    override fun write(input: String, output: OutputStream) {
        JsonFactory().createGenerator(output, JsonEncoding.UTF8).run {
            writeStartObject()
            writeStringField(fixedKey, input)
            writeEndObject()
            flush()
            close()
        }
    }
}

object Constants {
    const val fixedKey = "key"
    const val fixedValue = "value"
    const val outStreamFixedSize = 32
}
