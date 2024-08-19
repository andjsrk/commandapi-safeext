# commandapi-safeext
A better way to write commands using [CommandAPI](https://commandapi.jorel.dev/).

## Motivation
With vanilla CommandAPI, writing commands is error-prone and inconvenient:

```kt
commandAPICommand("some_cmd") {
    integerArgument("first")
    stringArgument("second")
    blockStateArgument("third")
    booleanArgument("fourth")
    integerArgument("fifth", optional = true)
    playerExecutor { player, args ->
        val first = args["first"] as String // oops, I forgot to update here after changing the type of the argument!
        val second = args["seocnd"] as String // oops, there is a typo in the argument name!
        val third = args["third"] as BlockState // oops, I cast the argument to wrong type! - https://commandapi.jorel.dev/9.5.0/argument_blockstate.html
        
        val fourth = args["fourth"] as Boolean
        // finally got the right way, but it is still inconvenient :(
        // it still requires me to manually cast the value even I wrote *boolean*Argument("fourth"), what?
        
        val fifth = args.getOptional("fifth").getOrNull() as Int?
        // I wrote `optional = true` above... why are you not so smart?
    }
}
```
... here commandapi-safeext is.

## Example
```kt
commandAPICommand("some_cmd") {
    val first = safeIntegerArgument("first")
    val second = safeStringArgument("seocnd") // although a typo still can exist, this does not affect getting the value of the argument!
    val third = safeBlockStateArgument("third")
    // skips the boolean argument 'fourth' that was present in the example above
    val fourth = safeIntegerArgument("fourth").optional()
    playerExecutor { player, args ->
        val first = args[first]
        //               ^ first: RequiredSafeArgument<IntegerArgument, Int>
        //               the argument itself contains type data, so there is no need to manually cast now!
        val second = args[second] // if there is a typo, *compiler* will catch that!
        val third = args[third] // since manual casting is no longer needed, mis-casting can never happen!
        val fourth = args[fourth]
        //  ^             ^ fourth: OptionalSafeArgument<IntegerArgument, Int>
        //  ^ fourth: Int?
        // now you are allowed to write concise, DRY code!
    }
}
```

## Major changes

### No `useNamespacedKey` parameters
Originally, functions like `biomeArgument` have `useNamespacedKey` parameter.
But they obstruct keeping consistency of return type; so they have split into separate functions.

### No `optional` parameters
To get proper argument type, `optional` parameters have split into separate methods.
