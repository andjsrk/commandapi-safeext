package io.github.andjsrk.commandapi.safeext

import dev.jorel.commandapi.arguments.Argument

/**
 * Indicates an argument that is optional.
 */
class OptionalSafeArgument<Arg: Argument<*>, R> internal constructor(index: Int): SafeArgument<Arg, R?>(index)
