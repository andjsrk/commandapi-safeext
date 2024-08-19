package io.github.andjsrk.commandapi.safeext

import dev.jorel.commandapi.arguments.Argument

/**
 * Indicates an argument that is required.
 */
class RequiredSafeArgument<Arg: Argument<*>, R> internal constructor(private val argument: Arg, index: Int): SafeArgument<Arg, R>(index) {
    /**
     * Turns the [RequiredSafeArgument] into optional.
     *
     * Warning: this method mutates [Argument]'s state; you must not reuse the [RequiredSafeArgument] after calling the method.
     */
    fun optional() =
        OptionalSafeArgument<Arg, R>(index)
            .also {
                argument.isOptional = true
            }
}
