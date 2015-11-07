// The main mathod called when the script starts
method main() {
    // Create new thread called "Thread-Test"
    testThread = new Thread("Thread-Test");
    // Thread block associated with 'testThread', executed when the thread starts
    thread(testThread){
        // Print thread's name
        System.print(testThread.getName());
    };
    // Start the thread
    testThread.start();

    // Get the current thread
    currentThread = Thread.currentThread();
    // Display the name of the current thread
    System.print(currentThread.getName());
}