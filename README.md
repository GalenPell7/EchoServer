# EchoServer
Multi-Threading

This project contains two different implementations for creating a threadpool to handle clients on an echo server. The two implmentations are using the keyword synchronization
or locks and condition methods.

Using the keyword synchronization within the method declaration will allow the method to be thread safe and as a result, the entirety of the method will be thread safe.

Critical Section - Code at which accesses a shared resource amoung threads and can only be accessed once at a time. Without Mutual exclusion, race conditions would happen
frequently and code would be non - deterministic.

For example: 

```
  public synchronized void addConnection(Connection connection) {
  
  ...some code...
  
  } // everything within the method pertains to the critical section of code.
  
```

With Locks and Conditions, The two are used together to allow for more flexibility of where critical sections are within methods themselves. Instead of having the entire
body of the method as the critical section, a Lock and Condition can precisely surround the neccassary lines of code that actually access the shared resource which can help 
speed of execution.

```
public Connection2 getConnection() {
            try {
                lock.lock();
            while (connections.isEmpty())
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
            return connections.removeFirst();
        }
```

Here, we specifically have the return statement outside of the condition but in an application where locking only parts of a method up, Lock and Conditions work well.
