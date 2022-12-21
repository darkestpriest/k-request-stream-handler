package request.handler

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Convenience abstract class to delegate coroutines handling.
 *
 * @param T represents the input type
 * @param U represents the output type
 */
abstract class HandlerStream<T, U>: RequestStreamHandler {

    /**
     * Handles lambda function request.
     *
     * @param input the function input business request.
     *
     * @return [U] the function output business response.
     */
    abstract fun doHandle(input: T): U

    /**
     * Parses the [InputStream] into a [T].
     *
     * @param input the function input business request.
     *
     * @return [T] parsed stream.
     */
    abstract fun parse(input: InputStream): T

    /**
     * Writes the [OutputStream] with the [input] content.
     *
     * @param input class to write into [output]
     * @param output stream to retrieve.
     */
    abstract fun write(input: U, output: OutputStream)

    override fun handleRequest(input: InputStream, output: OutputStream, context: Context?) {
        runBlocking {
            launch {
                doHandle(parse(input)).run {
                    write(this, output)
                }
            }
        }
    }
}
