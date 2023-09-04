Original [Slack message](https://kotlinlang.slack.com/archives/CJLTWPH7S/p1693561958242919):

> I noticed a very weird Compose behaviour with a Hilt-provided `ViewModel` when the reference of the VM is used in a lambda, like in `confirmValueChange` of `rememberModalBottomSheetState`.
> For example when I use the VM reference like this
> 
> ```kotlin
> val sheetState = rememberModalBottomSheetState(
>     confirmValueChange = {
>         viewModel.someCallback()
>         true
>     }
> )
> ```
> 
> the bottom sheet will never show when I call `sheetState.show()`. However when I use `rememberUpdatedState` on the VM the bottom sheet works.
>
> ```kotlin
> val updatedViewModel by rememberUpdatedState(viewModel)
> val sheetState = rememberModalBottomSheetState(
>     confirmValueChange = {
>         updatedViewModel.someCallback()
>         true
>     }
> )
> ```
> 
> I confirmed that `viewModel` is always the same instance, even after recomposition, so this is not the problem.
> The VM is provided with `hiltViewModel()` in a `NavHost`. When I change this to a “normal” VM without Hilt injection, the bottom sheet works even without `rememberUpdatedState!` This is all very confusing.
> What is even more confusing is that I can reproduce this in a small [sample project](https://github.com/svenjacobs/compose-remember-updated-state/) but sometimes the variant without `rememberUpdatedState` works, sometimes when I recompile the app it doesn’t 🤷🏼‍♂️ The relevant code can be found in [MainScreen](https://github.com/svenjacobs/compose-remember-updated-state/blob/15abb09740bc98568be477e6c513cdb3c7c0cabe/app/src/main/java/com/svenjacobs/sample/ui/MainScreen.kt#L34-L42).
>
> https://github.com/svenjacobs/compose-remember-updated-state/blob/15abb09740bc98568be477e6c513cdb3c7c0cabe/app/src/main/java/com/svenjacobs/sample/ui/MainScreen.kt#L34-L42
>
> Has anyone else encountered this strange and random behaviour?
