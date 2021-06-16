# Fake Stark Application

Application to simulate the operation of the Stark network.

## Documentation

* The documentation of the application can be found in the [directory](../Documents)


## Installation

* Run the following commands in the root [directory](../FakeStark) of this project.
    
    * Clone the project
       > ```
       > git clone https://github.com/Candres1019/StarkAdapterPrototype.git
       > ```

    * Build the docker image
       > ```
       >  docker build --tag fakestarkimg .
       > ```

    * Run the image in a new container
       > ```
       >  docker run -d -p 8002:8002 --name fakestarkcontainer fakestarkimg
       > ```


## Build with

* [Java](https://www.java.com/es/) — Programming Language.
* [Maven](https://maven.apache.org/) — Dependency management.
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) — Development Environment.
