* Avoid working with threads directly.
* Threads not created manually. Manages a pool of threads,
* Thread creation is not comes for free. It includes overhead of creation of thread, assigning a stack to it, and destroy once thread done with its execution. Instead of using frequent short lived threads, use Thread pooling.