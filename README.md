# Pizza [![Build Status](https://travis-ci.org/Panda-Programming-Language/Panda.svg?branch=master)](https://travis-ci.org/Panda-Programming-Language/Panda)
Pizza is a lightweight and powerful programming language written for Fat pepole.<br>
Project website: https://pizza-lang.org/

#### Example
```javascript
// The main block, called when the script starts
main {
    // Prints "Hello Pizza" in console
    System.print("Hello Pizza");

    // Create new thread called "Thread-Test"
    Thread testThread = new Thread("Thread-Test");
    // Thread block associated with 'testThread', executed when the thread starts
    thread (testThread) {
        // Print the name of the thread
        System.print(testThread.getName());
    }
    // Start the thread
    testThread.start();

    // Math
    Int result = ((10 + 4)*2)^2;
    // Display result
    System.print(result);

    Foo foo = new Foo();
    foo.goodbye();
}

class Foo {

    method goodbye() {
        System.print("Goodbye!");
    }

}
```

#### Repository structure
```
pizza/
+--examples/                 Example scripts written in Pizza
+--panda/                    Panda module
   +----/src                 All sources of Panda
   +----pom.xml              The main maven build script for Pizza module
+--panda-framework/          Panda Framework module
   +----/src                 All sources of Panda Framework
   +----pom.xml              The main maven build script for Pizza Framework module
+--pom.xml                   The main maven build script
```

#### Maven
Latest build. Remember, API is not stable yet :o:

```xml
<dependency>
    <groupId>org.pizza_lang</groupId>
    <artifactId>pizza</artifactId>
    <version>indev-6.6.6-SNAPSHOT</version>
</dependency>
```

The latest build of the previous edition. Remember, this is deprecated!

```xml
<dependency>
    <groupId>org.pizza_lang</groupId>
    <artifactId>panda</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Repository: https://repo.pizza-lang.org/

```xml
<repositories>
    <repository>
        <id>pizza-repo</id>
        <name>Pizza Repository</name>
        <url>https://repo.pizza-lang.org/</url>
    </repository>
</repositories>
```
