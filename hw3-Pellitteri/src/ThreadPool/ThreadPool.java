package ThreadPool;

import java.util.LinkedList;

/**
 * @author Galen Pellitteri
 *
 * ThreadPool Class is called when the server is run and contains two inner classes (WorkQueue and WorkerThread).
 * When ThreadPool is called, A WorkQueue is initialized and an array of WorkerThreads is created with the size of three.
 * Upon adding a Connection, the connection is stored within the WorkQueue and three new WorkerThreads are created,
 * inserted and started.
 */

public class ThreadPool {

    private WorkQueue workQueue;
    private final int size = 3;
    private WorkerThread[] threads = new WorkerThread[this.size];

    public ThreadPool() {
        workQueue = new WorkQueue();
        for (int i = 0; i < size; i++) {
            WorkerThread thread = new WorkerThread();
            threads[i] = thread;
            threads[i].start();
        }
    }

    /**
     * Method to Add Connection to WorkQueue
     * @param connection to be added.
     */
    public void addConnection(Connection connection) {
        workQueue.addConnection(connection);
    }

    /**
     * Inner Class WorkQueue that contains a LinkedList of Connections. WorkQueue contains two synchronized methods
     * that handle adding and getting connections to be run.
     */

    public static class WorkQueue {

        private LinkedList<Connection> connections;

        public WorkQueue() {
            connections = new LinkedList<Connection>();
        }

        /**
         * getConnection method returns the front of the connections list and removes it.
         * @return the first in the connections list and remove it from connections.
         */
        public synchronized Connection getConnection() {
            while (connections.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           }
            return connections.removeFirst();
        }

        /**
         * addConnection adds a Connection to the list.
         * @param connection to be added.
         */
        public synchronized void addConnection(Connection connection) {
            connections.add(connection);
            notifyAll();
        }
    }

    /**
     * Inner Class WorkerThread extends the Thread Class and uses the run method to get a connection from the
     * WorkQueue and runs it.
     */
    public class WorkerThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                workQueue.getConnection().run();
                }
            } catch (Exception e) {
                // Nothing yet
            }
        }
    }
}
