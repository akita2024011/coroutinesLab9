# Laboratory Work 9: Coroutines Introduction

Practice work solution for Kotlin Coroutines introduction questions.

## Explanation: Suspending vs. Blocking in Coroutines

```kotlin
/*
 * Explanation of Suspending vs. Blocking:
 *
 * 1. Blocking (e.g. Thread.sleep):
 *    Blocks the current OS thread completely. No other task or coroutine can run 
 *    on this thread while it is blocked. On the Android UI thread, blocking causes 
 *    the app to freeze (ANR).
 *
 * 2. Suspending (e.g. delay or suspend functions):
 *    Pauses the execution of the coroutine without blocking the underlying OS thread.
 *    The thread is free to execute other coroutines or handle UI events. When the 
 *    delay/suspension completes, the coroutine resumes execution.
 */
```

## 5 Coroutine Builders Cheat Sheet

| Builder | Return Type | Blocks Thread? | Waits for Children? | Usage |
| :--- | :--- | :--- | :--- | :--- |
| `launch` | `Job` | No | No | Fire-and-forget background job without a return value. |
| `async` | `Deferred<T>` | No | No (via `.await()`) | Concurrent task returning a computed result value. |
| `runBlocking` | `T` | **Yes** | Yes | Bridge blocking code to coroutine code (e.g. in `main()`). Do NOT use on UI thread. |
| `coroutineScope` | `T` | No (suspends) | Yes | Scoped builder inside suspend functions; waits for all launched children. |
| `withContext` | `T` | No (suspends) | Yes | Switch coroutine context/dispatcher and return result. |

---

## Questions 1 – 15

### Question 1. First coroutine
- **Predicted Output:**
  ```text
  Order accepted
  Coffee ready
  ```
- **Actual Output:**
  ```text
  Order accepted
  Coffee ready
  ```
- **Explanation:** `launch` starts a coroutine asynchronously. Main body prints `"Order accepted"` immediately, while the child coroutine waits 1 second with `delay(1000)` without blocking the thread and then prints `"Coffee ready"`.

---

### Question 2. Three baristas
- **Actual Output (delay):**
  ```text
  --- With delay ---
  Espresso start
  Tea start
  Latte start
  Tea end
  Latte end
  Espresso end
  ```
- **Actual Output (Thread.sleep):**
  ```text
  --- With Thread.sleep ---
  Espresso start
  Espresso end
  Tea start
  Tea end
  Latte start
  Latte end
  ```
- **Explanation:** `delay` frees the single thread allowing all three coroutines to run concurrently, while `Thread.sleep` blocks the thread so each coroutine runs sequentially.

---

### Question 3. Chain of suspend functions
- **Actual Output:**
  ```text
  Login flow started
  Step 1: User loaded
  Step 2: Password checked
  Step 3: Profile opened
  Login flow completed
  ```
- **Explanation:** Sequential calling of suspend functions pauses execution at each step without changing line execution order.

---

### Question 4. Calling suspend from normal code
- **Compile Error Message:** `Suspend function 'fetchUserData' should be called only from a coroutine or another suspend function`
- **Fix 1:** Mark caller function as `suspend`.
- **Fix 2:** Use `runBlocking` or `CoroutineScope.launch`.
- **Android UI Thread Warning:** `runBlocking` must never be used on the Android UI thread as it blocks the main thread and freezes the app UI.

---

### Question 5. Suspend is not a thread
- **Actual Output:**
  ```text
  Thread before suspend call: main
  Thread inside suspend call: main
  ```
- **Explanation:** Marking a function `suspend` does not switch to a background thread; it ran on `main`.
- **Compiler Hidden Parameter:** The compiler adds a `Continuation` parameter (`Continuation completion`) to every suspend function.

---

### Question 6. Ten downloads, one thread
- **Actual Output:**
  ```text
  download 1 started on main
  download 2 started on main
  download 3 started on main
  download 4 started on main
  download 5 started on main
  download 6 started on main
  download 7 started on main
  download 8 started on main
  download 9 started on main
  download 10 started on main
  download 1 finished on main
  download 2 finished on main
  download 3 finished on main
  download 4 finished on main
  download 5 finished on main
  download 6 finished on main
  download 7 finished on main
  download 8 finished on main
  download 9 finished on main
  download 10 finished on main
  ```
- **Decision:** This is **concurrency**, because all 10 downloads run concurrently on a single thread by yielding during delay, rather than executing in parallel on multiple threads/cores.

---

### Question 7. Order of launch
- **Predicted Output:** `1`, `4`, `2`, `5`, `3`
- **Actual Output:**
  ```text
  1
  4
  2
  5
  3
  ```
- **Explanation:** `launch` dispatches the coroutine to the event loop. Main prints `4` and calls `delay(50)` which suspends main. The launched coroutine then runs, prints `2` and delays 100ms. Main's 50ms delay completes first and prints `5`, followed by the child's delay finishing and printing `3`.

---

### Question 8. Parent waits for child
- **Actual Output:**
  ```text
  parent body done
  child done
  report completed
  ```
- **Structured Concurrency Rule:** A parent coroutine does not complete until all of its child coroutines complete.

---

### Question 9. Screen closed, requests cancelled
- **Actual Output:**
  ```text
  Request 1 started
  Request 2 started
  Cancelling parent coroutine...
  Request 1 cleanup in finally
  Request 2 cleanup in finally
  end
  ```
- **Explanation:** Cancelling the parent coroutine automatically propagates cancellation down to all child coroutines.

---

### Question 10. Profile ready
- **Actual Output:**
  ```text
  Settings loaded (100ms)
  Avatar loaded (200ms)
  Profile ready
  end of main
  Background task after scope
  ```
- **Explanation:** `coroutineScope` suspends until both child tasks finish, so `"Profile ready"` prints after both loads finish. The coroutine launched outside the scope runs asynchronously, so `"end of main"` prints first.

---

### Question 11. Reusable counter
- **Actual Output:**
  ```text
  launched
  Counter A: 1
  Counter B: 1
  Counter A: 2
  Counter B: 2
  Counter A: 3
  Scope completed
  ```
- **Why Receiver is Needed:** The `CoroutineScope` receiver allows the extension function to launch new coroutines in the caller's scope for structured concurrency.

---

### Question 12. Returns at once or waits
- **Actual Output:**
  ```text
  Calling greetInBackground...
  Line right after greetInBackground call (returned immediately)

  Calling greetAll...
  Sequential Hello, Bob!
  Sequential Hello, Charlie!
  Line right after greetAll call (returned only after all finished)
  ```
- **Explanation:** `greetInBackground` is a `CoroutineScope` extension function that launches a job and returns immediately. `greetAll` is a suspend function using `coroutineScope` that suspends until all greetings complete.

---

### Question 13. Five builders
- **Actual Output:**
  ```text
  1. runBlocking demonstration:
     Inside runBlocking
     launch returns: StandaloneCoroutine{Active}@...
     async returns: DeferredCoroutine{Active}@...
     await() returned: Result from async
     coroutineScope returns: Result from coroutineScope
     withContext returns: Result from withContext
  runBlocking returned: runBlocking result string
  ```

---

### Question 14. Job or Deferred
- **Actual Output:**
  ```text
  --- Launch Version ---
  Launch calculated result inside coroutine: 42
  Job returned: StandaloneCoroutine{Completed}@... (Cannot extract return value from Job)

  --- Async Version ---
  Deferred object returned immediately: DeferredCoroutine{Active}@...
  Calculated result obtained via await(): 42
  ```
- **Job vs Deferred:** A `Job` represents a coroutine without a return value. A `Deferred<T>` represents a job that produces a result of type `T` accessible via `.await()`.

---

### Question 15. Three exports in a scope
- **Actual Output:**
  ```text
  Start
  Export Y finished
  Export Z finished
  Export X finished
  All done
  ```
- **Explanation:** All three exports run concurrently inside `coroutineScope`, finishing in time order (Y 100ms, Z 200ms, X 300ms). `coroutineScope` waits for all to complete before printing `"All done"`.

---

## Extra Questions

### Extra 1. Delay or sleep: measure it
- **Actual Output:**
  ```text
  Time with delay: ~300ms (Exact: 308ms)
  Time with Thread.sleep: ~900ms (Exact: 915ms)
  ```
- **Explanation:** `delay` suspends concurrently taking ~300ms total, while `Thread.sleep` blocks sequentially taking ~900ms.

---

### Extra 2. Three levels of coroutines
- **Actual Output:**
  ```text
  child 1 done
  child 2 done
  grandchild done
  scope done
  ```
- **Coroutine Tree Diagram:**
  ```text
  coroutineScope
  ├── Child 1
  │   └── Grandchild (delay 200ms)
  └── Child 2 (delay 100ms)
  ```
- **Explanation:** `coroutineScope` waits for all children and grandchildren to finish before completing.

---

### Extra 3. No scope, no launch
- **Compile Error Message:** `Unresolved reference: launch`
- **Output with runBlocking:**
  ```text
  Starting main with runBlocking...
  Hello from launch inside runBlocking
  Finished main
  ```
- **Explanation:** `runBlocking` creates a root coroutine scope and blocks the main thread until all inner coroutines complete.

---

### Extra 4. What the compiler sees
- **Decompiled Java Signature:**
  ```java
  public static final Object loadUserById(int id, Continuation completion)
  ```
- **Extra Parameter:** `Continuation completion`
- **Return Type:** `Object`
- **Explanation:** The compiler transforms suspend functions into state machines using Continuation Passing Style (CPS). The `Continuation` parameter carries callback state and resumption logic.

---

### Extra 5. Greet everybody
- **Actual Output:**
  ```text
  before
  Hello, Ada
  Hello, Linus
  Hello, Grace
  after
  ```
- **Explanation:** `greetEverybody` uses `coroutineScope` and suspends until all launched child greetings finish. Thus `"after"` is printed last.
