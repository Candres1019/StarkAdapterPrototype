# Stark Adapter Application

Stark network adapter.

## Documentation

* The documentation of the application can be found in the [directory](../Documents)


## Installation

* Run the following commands in the root [directory]() of this project.
    
    * Clone the project
       > ```
       > git clone https://github.com/Candres1019/StarkAdapterPrototype.git
       > ```

    * Build the docker image
       > ```
       >  docker build --tag starkadapterimg .
       > ```

    * Run the image in a new container
       > ```
       >   docker run -d -p 8001:8001 --network="host" --name starkadaptercontainer starkadapterimg
       > ```


## Build with

* [Java](https://www.java.com/es/) — Programming Language.
* [Maven](https://maven.apache.org/) — Dependency management.
* [JUnit](https://junit.org/junit5/) — Unit tests.
* [Mockito](https://site.mockito.org/) — Integration tests.
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) — Development Environment.
