package io.github.andjsrk.commandapi.safeext

import dev.jorel.commandapi.arguments.Argument

sealed class SafeArgument<Arg: Argument<*>, R>(val index: Int)
